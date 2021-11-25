package server.server_side;

import global.mes.message;
import global.operation;
import server.dataBase.DBconnecTion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Enumeration;

class serverConnection extends Thread implements AutoCloseable{
    /**
     *  volatile修饰符用来保证其它线程读取的总是该变量的最新的值
     *  程序连接标识
     */
    private volatile boolean exit_server;
    //用户信息
    private String clientIP;
    private int clientPort;
    //端口连接
    private  int SERVER_PORT=31010;
    private Socket connection; // connection to client
    private ServerSocket serverSocket;
    protected server_UserProcess user_process;
    protected server_AdminProcess admin_process;
    private serverFrame serverFrame;
    //输入输出流
    private  ObjectOutputStream output; // output stream to client
    private  ObjectInputStream input; // input stream from client

    //构造连接
    public serverConnection(serverFrame frame) throws IOException {
        this.exit_server=true;
        this.serverFrame=frame;
        serverSocket=new ServerSocket(SERVER_PORT,100);
        this.user_process =new server_UserProcess(this);
        this.admin_process=new server_AdminProcess(this);
    }
    /**
     * 线程实现交互
     */
    public void run(){
        try{
            connectDataBase();
            waitForConnection();
            getStreams();
            serverProcess();
        }catch (IOException e)
        {
            e.printStackTrace();
            e.toString();
            serverFrame.disMessageinArea( "Server terminated connection" );
        } // end catch
    }
    /**
     * TODO 连接数据库
     */
    public void connectDataBase() {
        try {
            boolean result=DBconnecTion.connectToDatebase();
            if(result)
               serverFrame.disMessageinArea("数据库已连接");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 等待连接
     * @throws IOException
     */
    private void waitForConnection() throws IOException {
        connection=serverSocket.accept();
        //获取设置ip地址，端口号
        clientIP = connection.getInetAddress().getHostAddress();
        clientPort =connection.getLocalPort() ;
        System.out.println("服务器端口号连接为"+clientPort);
        //提示信息
        serverFrame.disMessageinArea( "Connection received from: " + connection.getInetAddress().getHostName() );
    } // end method waitForConnection

    /**
     * 获取输入输出流
     * @throws IOException
     */
    private void getStreams() throws IOException {
        // set up Objectstream for objects
        output = new ObjectOutputStream( connection.getOutputStream() );
        input = new ObjectInputStream( connection.getInputStream() );
        output.flush();
        serverFrame.disMessageinArea( "Got I/O streams\n" );
    } // end method getStreams

    /**
     * 和客户端交互
     */
   public String makeTips(String tipWords){
        String tips = tipWords+" comes from [ " + getClientIP()+ " : " + getClientPort()+ " ]\n";
        System.out.println(tips);
        return tips;
    }
    private void serverProcess(){
        //server_UserProcess userProcess=new server_UserProcess(this);
        String tips="Get to server";
        message mes=new message(makeTips(tips));
        this.sendData(mes);
        while(exit_server){
            message callmes=this.getData();
            String oprea=callmes.getFlag().getOperaTion();
            serverFrame.disMessageinArea(makeTips(oprea));

            Enumeration<operation.USER_OPERATION_ENUM> hasUserOpera=new operation().getUserOperas();
            boolean adminopera=true;
            while (hasUserOpera.hasMoreElements()){
                if(oprea.equals(hasUserOpera.nextElement().getOperation())){
                    this.user_process.processConnection(callmes);
                    adminopera=false;
                    break;
                }
            }
            if(adminopera){
                admin_process.processConnection(callmes);
            }
        }
    }
    /** send ro get message via client
     * 接发信息经由客户端
     * @param message
     */
    protected  void sendData(message message)
    {
        try
        {
            output.writeObject(  message );
            output.flush(); // flush output to client
            serverFrame.disMessageinArea( "SERVER>>> " + message +"\n");
        } // end try
        catch ( IOException ioException )
        {
            ioException.toString();
            ioException.printStackTrace();
           //serverFrame.displayMessage( "Error writing object in sendData" );
        } // end catch
        finally {
            //closeStreams("sendData IO close");
        }
    } // end method sendData

    protected  message getData()
    {
        message message=null;
        try {
            //getStreams();
            message = (message) input.readObject();
            serverFrame.disMessageinArea("comes from Client:\n");
            serverFrame.disMessageinArea((message.toString()));

        }catch(IOException ioException){
            ioException.printStackTrace();
            ioException.toString();
            //serverFrame.displayMessage( "Error writing object in getData" );
        } catch (ClassNotFoundException notfind) {
            notfind.printStackTrace();
            notfind.toString();
        } finally{
            //closeStreams("sendData IO close");
        }
        return message;
    } // end method getData

    /** close streams
     * 关闭连接Socket流连接
     */
    private void closeStreams(String closeInfo)
    {
        System.out.println(closeInfo);
        serverFrame.disMessageinArea(closeInfo);
        try
        {
            output.close(); // close output stream
            input.close(); // close input stream
        } // end try
        catch ( IOException ioException )
        {
            ioException.printStackTrace();
        } // end catch
    } // end method closeStreams
    @Override
    public void close() throws Exception {
        closeStreams("自动关闭服务器端连接");
    }

    protected String getClientIP() {
        return clientIP;
    }
    protected int getClientPort() {
        return clientPort;
    }

    public  ObjectOutputStream getOutput() {
        return this.output;
    }
}
