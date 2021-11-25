package server.dataBase;

import java.sql.*;

public class DBconnecTion implements AutoCloseable {

    protected static boolean connectToDB=false;
    protected static Connection connection;

    //数据库连接
    public static String driverName = "com.mysql.cj.jdbc.Driver"; // 加载数据库驱动类
    public static String url = "jdbc:mysql://localhost:3306/account?serverTimezone=GMT%2B8"; // 声明数据库的URL
    public static  String user="root";                                      // 数据库用户
    public static String password="090014";  //密码

    /**
     * TODO 创建数据库连接
     * @throws Exception
     */
    public static boolean connectToDatebase()throws ClassNotFoundException,SQLException{
        Class.forName(driverName);//加载数据库
        connection= DriverManager.getConnection(url,user,password);//建立数据库连接
        connectToDB=true;
        return connectToDB;
    }

    /**
     * TODO 关闭数据库连接(自动调用)
     */
    protected void disconnectToDB(){
        if (connectToDB) {
            // close Statement and Connection
            try {
                dataProcessing.preStatement.close();
                dataProcessing.statement.close();
                dataProcessing.resultSet.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                connectToDB = false;
            }
        }
    }
    @Override
    public void close() throws Exception {
        disconnectToDB();
    }
}
