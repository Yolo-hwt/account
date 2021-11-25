package client;

import global.MainFrame;
import global.mes.accountUser;
import global.mes.backLog;
import global.mes.message;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

/**
 * 客户端类
 * client
 */
public class client_side implements AutoCloseable{
    //客户端全局变量
    public static client_side Myclient;
    //传递信息
    private ObjectOutputStream output; // 输出流 (to server)
    private ObjectInputStream input; // 输入流 (from server)

    //接口信息
    private String chatServer; // host server for this application
    private Socket client; // socket to communicate with server
    private static final int port=31010;//程序接口(port)

    public client_side(String host ) { chatServer = host; } // end Client constructor

    public static void main(String[] args) {
        Myclient=new client_side("127.0.0.1");
        Myclient.runClient();
    }

    /**
     * TODO achieve client
     *实现客户端
     */
    public void runClient() {
        new Thread(() -> {
            try {
                connectToServer(); // 连接服务器
                getStreams(); // 获取流
                getData();
                processConnection(); // process connection
            } // end try
            catch (IOException ioException) {
                ioException.printStackTrace();
            } // end catch
        }).start();
    }// end method runClient

    /**
     *TODO 创建用户登录GUI界面
     * @throws IOException
     */
    private static void processConnection() {
       //登录界面
        new MainFrame().setVisible(true);
    } // end method processConnection

    /**
     *TODO 连接服务器
     * @throws IOException
     */
    private void connectToServer() throws IOException {
        displayMessage( "尝试连接服务器" );

        // create Socket to make connection to server
        client = new Socket( chatServer, port );

        // display connection information
        displayMessage( "Try connected to: " +
                client.getInetAddress().getHostName());
    } // end method connectToServer

    /**
     *TODO 获得输入输出流
     * @throws IOException
     */
    private void getStreams() throws IOException {
        input = new ObjectInputStream( client.getInputStream() );
        output = new ObjectOutputStream( client.getOutputStream() );
        output.flush(); // flush output buffer to send header information
        displayMessage( "连接成功\nGot I/O streams" );
    } // end method getStreams

    /**
     * TODO 获取服务器单个Message信息
     * @return
     */
    //接收信息
    public message getData(){
        message message=null;
        try{
            message=(message)input.readObject();
            displayMessage("收到来自服务器如下数据");
            displayMessage(""+message.toString()+"\n");
        }catch(ClassNotFoundException|IOException e){
            e.toString();
            e.printStackTrace();
            displayMessage( "Error writing object in getData" );
        }finally {
            //closeConnection("getData IO连接关闭");
        }
        return message;
    }// end method getData
    //发送信息
    public void sendData(message message) {
        try
        {
            output.writeObject(message);
            output.flush(); // flush output to client
            displayMessage( "CLIENT>>> " + message.toString() );
        } // end try
        catch ( IOException ioException )
        {
            ioException.toString();
            ioException.printStackTrace();
            displayMessage( "Error writing object in sendData" );
        } // end catch
        finally {
            //closeConnection("sentData IO连接关闭");
        }
    } // end method sendData

    /**
     *TODO 关闭连接
     */
    private void closeConnection(String closeInfo)  {
        displayMessage(closeInfo);
        try
        {
            if(output!=null)
                output.close(); // close output stream
            if(input!=null)
                input.close(); // close input stream
        } // end try
        catch ( IOException ioException )
        {
            ioException.printStackTrace();
        } // end catch
    } // end method closeConnection
    public void close(){
        closeConnection("自动关闭客户端连接");
    }

    /**
     *TODO 终端显示信息
     * @param messageToDisplay
     */
    public static void displayMessage( final String messageToDisplay ) {
        System.out.println(messageToDisplay);
    }// end method displayMessage

    /**
     * TODO:获取所有用户信息
     * @return
     */
    public  Vector<accountUser> getAllUser(){
        try {
            Vector<accountUser> users=(Vector<accountUser>)input.readObject();
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  Vector<backLog> getBackLogs(){
        try {
            Vector<backLog> backlogs=(Vector<backLog>)input.readObject();
            return backlogs;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
