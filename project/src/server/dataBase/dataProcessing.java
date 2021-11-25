package server.dataBase;

import java.sql.*;
import java.util.Vector;

import global.*;
import global.mes.accountFlag;
import global.mes.accountUser;
import global.mes.backLog;

public class dataProcessing {
    protected static Statement statement;
    protected static ResultSet resultSet;
    protected static PreparedStatement preStatement;

    /**
     * TODO:匹配用户名密码查找用户
     * @param phonenums
     * @param password
     * @return
     * @throws SQLException
     */
    public static accountUser searchUserByNP(String phonenums, String password)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );
        accountUser temp=null;
        /*String sql="SELECT account_info.*,loss,frozen" +
                " FROM account_info LEFT OUTER JOIN account_status" +
                " ON account_info.cardNumber = ? AND account_info.passWord = ?"
                +"AND account_info.cardNumber=account_status.cardNumber";
        */
        String sql="SELECT * FROM account_info WHERE phoneNum =? AND passWord=?";
        System.out.println(sql);
        preStatement=DBconnecTion.connection.prepareStatement(sql);
        preStatement.setString(1,phonenums);
        preStatement.setString(2,password);
        resultSet= preStatement.executeQuery();

        if(resultSet.next()!=false){
            String cardNumber=resultSet.getString("cardNumber");
            String phoneNunm=resultSet.getString("phoneNum");
            String Name=resultSet.getString("userName");
            String passWord=resultSet.getString("passWord");
            Timestamp loginT=resultSet.getTimestamp("loginTime");

           temp=new accountUser(cardNumber,passWord,Name,phoneNunm,loginT);
        }
        return temp;
    }

    /**
     * TODO:插入新用户
     * @param user
     * @return
     */
    public static boolean insertUser(accountUser user, accountFlag flag)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );
        int res1=0;
        int res2=0;
        int res3=0;
        boolean result=false;
        String sql1="INSERT INTO account_info VALUES(?,?,?,?,?)";
        preStatement=DBconnecTion.connection.prepareStatement(sql1);
        preStatement.setString(1,user.getCardNumber());
        preStatement.setString(2,user.getPassWords());
        preStatement.setString(3,user.getPhoneNum());
        preStatement.setString(4,user.getUserName());
        preStatement.setTimestamp(5,user.getLoginTime());
        res1=preStatement.executeUpdate();

        String sql2="INSERT INTO account_status VALUES(?,?,?,?)";
        preStatement=DBconnecTion.connection.prepareStatement(sql2);
        preStatement.setString(1,user.getCardNumber());
        preStatement.setString(2,user.getPhoneNum());
        preStatement.setInt(3,0);//初始冻结标识，挂失标识为0
        preStatement.setInt(4,0);
        res2=preStatement.executeUpdate();

        String sql3="INSERT INTO account_money VALUES(?,?,?,?,?,?)";
        preStatement=DBconnecTion.connection.prepareStatement(sql3);
        preStatement.setString(1,user.getPhoneNum());
        preStatement.setDouble(2,flag.getAmount());
        preStatement.setString(3,"save");
        preStatement.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
        preStatement.setDouble(5,flag.getAmount());
        preStatement.setInt(6,1);//lastopera
        res3=preStatement.executeUpdate();

        if(res1==1)
            if(res2==1)
                if(res3==1)
                    result=true;

        return result;
    }

    /**
     * TODO:存取款
     * @param phoneNum
     * @param operation
     * @param money
     * @return
     */
    public static boolean updateMoney(String phoneNum,String operation,Double money)throws SQLException{
        //创建新卡时候加上一条记录，标记为存款，金额为10
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );
        boolean result=false;
        int flag=1;
        double balance=0;
        String saveOruse="save";//默认为存款
        //获取余额
        String getbalabce="SELECT balance FROM account_money WHERE phoneNum =? AND lastopera=?";
        preStatement=DBconnecTion.connection.prepareStatement(getbalabce);
        preStatement.setString(1,phoneNum);
        preStatement.setInt(2,1);
        resultSet=preStatement.executeQuery();
        if(resultSet.next()!=false){
            balance=resultSet.getDouble("balance");
            System.out.println(phoneNum+" have balabce: "+balance);

        }
        //识别操作sava or use
        if(operation.equals(global.operation.USER_OPERATION_ENUM.withdrawMoney.getOperation())){
            saveOruse="use";//取款
            flag=-flag;//flag=-1
        }
        balance+=(flag*money);
        System.out.println("new balance: "+balance);

        String update_last="UPDATE account_money SET lastopera=0 WHERE phoneNum=? AND lastopera=?";
        preStatement=DBconnecTion.connection.prepareStatement(update_last);
        //preStatement.setInt(1,1);
        preStatement.setString(1,phoneNum);
        preStatement.setInt(2,1);
        int up=preStatement.executeUpdate();
        System.out.println("up="+up);
        if(up==1){
            System.out.println("修改标识成功！");
            Timestamp moneyTime=new Timestamp(System.currentTimeMillis());
            String updatemoney="INSERT INTO account_money VALUES(?,?,?,?,?,?)";
            preStatement=DBconnecTion.connection.prepareStatement(updatemoney);
            //preStatement.setString(1,cardNumber);
            preStatement.setString(1,phoneNum);
            preStatement.setDouble(2,money);
            preStatement.setString(3,saveOruse);
            preStatement.setTimestamp(4,moneyTime);
            preStatement.setDouble(5,balance);//余额
            preStatement.setInt(6,1);//lastopera=1 最新操作
            int res=preStatement.executeUpdate();
            if(res==1){
                result=true;
            }
        }
        else{
            System.out.println("updateMoney false");
        }
        return result;
    }

    /**
     * TODO:更新密码
     * @param phoneNum
     * @param password
     * @return
     * @throws SQLException
     */
    public static boolean updatePassword(String phoneNum,String password)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );
        boolean result=false;
        String update_flag="UPDATE account_info SET passWord=? WHERE phoneNum=?";
        preStatement=DBconnecTion.connection.prepareStatement(update_flag);
        preStatement.setString(1,password);
        preStatement.setString(2,phoneNum);
        int up=preStatement.executeUpdate();
        if(up==1){
            result=true;
        }
        else{
            System.out.println("updateflag false");
        }
        return result;
    }

    /**
     * TODO:插入用户待办列表
     * @param phoneNum
     * @param applyOperation
     * @return
     */
    public static boolean insertUserBackLog(String phoneNum,String desc,String applyOperation)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );
        boolean result=false;
        Timestamp time=new Timestamp(System.currentTimeMillis());
        String sql="INSERT INTO admin_backlog VALUES(?,?,?,?,?,?,?)";
        preStatement=DBconnecTion.connection.prepareStatement(sql);
        preStatement.setString(1,phoneNum);
        preStatement.setString(2,applyOperation);
        preStatement.setTimestamp(3,time);//reportTime
        preStatement.setTimestamp(4,time);//finishTime 预置默认
        preStatement.setInt(5,0);//solve=0 未完成
        preStatement.setString(6,desc+"待处理");//describe
        preStatement.setInt(7,0);//hasTold=0 结果未告知用户

        int res=preStatement.executeUpdate();
        if(res==1){
            result=true;
        }
        return result;
    }


    /************************************************************************************/

    /**
     * TODO:查找管理员
     * @param adminNum
     * @param pass
     * @return
     * @throws SQLException
     */
    public static accountUser searchAdmin(String adminNum,String pass)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );
        accountUser temp=null;
        String sql="SELECT * FROM admin_info WHERE admin =? AND passWord=?";
        preStatement=DBconnecTion.connection.prepareStatement(sql);
        preStatement.setString(1,adminNum);
        preStatement.setString(2,pass);
        resultSet= preStatement.executeQuery();

        if(resultSet.next()!=false){
            String Name=resultSet.getString("admin");
            String password=resultSet.getString("passWord");

            temp=new accountUser(Name,password);
            temp.setPhoneNum(Name);
            System.out.println("查找到temp:"+temp.toString());
        }
        return temp;
    }

    /**
     * TODO:根据字段名查找 backlogs
     * @return
     * @throws SQLException
     */
    public static Vector<backLog> listBacklogs()throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );

        Vector<backLog>logs;
        statement=DBconnecTion.connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String sql="SELECT * FROM admin_backlog";//solve=0表示未处理
        resultSet=statement.executeQuery(sql);
        //if(resultSet!=null){
            logs= new Vector<>();
        //} else {
           // return null;
        //}
        while (resultSet.next()){
            String phoneNum=resultSet.getString("phoneNum");
            String operaTion=resultSet.getString("operaTion");
            Timestamp reportTime=resultSet.getTimestamp("reportTime");
            Timestamp finishTime=resultSet.getTimestamp("finishTime");
            int solve=resultSet.getInt("solve");
            String describe=resultSet.getString("userDesc");
            int hasTold=resultSet.getInt("hasTold");

            backLog temp=new backLog(phoneNum,operaTion,reportTime,finishTime,solve,describe,hasTold);
            logs.addElement(temp);
        }
        return logs;
    }

    /**
     * TODO:更新一条backlog(处理完成阶段)
     * @param phoneNum
     * @param operaTion
     * @param desc
     * @return
     * @throws SQLException
     */
    public static boolean updateBacklogs
            (String phoneNum,String operaTion,String desc)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );

        boolean result=false;
        String userOper;
        Timestamp finish=new Timestamp(System.currentTimeMillis());
        userOper=operation.adminToUser(operaTion);

        String sql="UPDATE admin_backlog SET solve=1,userDesc=?,finishTime=? WHERE phoneNum=? AND operaTion=? AND solve=0";
        preStatement=DBconnecTion.connection.prepareStatement(sql);
        preStatement.setString(1,desc);
        preStatement.setTimestamp(2,finish);
        preStatement.setString(3,phoneNum);
        preStatement.setString(4,userOper);
        int res=preStatement.executeUpdate();
        if(res==1){
            result=true;
        }
        return result;
    }
    //TODO：更新 hasTold(已告知用户)
    public static boolean update_hasTold
            (String phoneNum,String operaTion)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );
        boolean result=false;
        String sql="UPDATE admin_backlog SET hasTold=1 WHERE phoneNum=? AND operaTion=? AND solve=1 AND hasTold=0";
        preStatement=DBconnecTion.connection.prepareStatement(sql);
        preStatement.setString(1,phoneNum);
        preStatement.setString(2,operaTion);
        int res=preStatement.executeUpdate();
        if(res==1){
            result=true;
        }
        return result;
    }

    /**
     * TODO:更新冻结卡挂失卡标识
     * @param phoneNum
     * @param flagName
     * @param status
     * @return
     * @throws SQLException
     */
    public static boolean updateflag(String phoneNum,String flagName,int status)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );

        boolean result=false;
        String opera;
        if(flagName.equals(operation.ADMIN_OPERATION_ENUM.updateLossBacklog.getOperation()))
            opera="loss";
        else if(flagName.equals(operation.ADMIN_OPERATION_ENUM.updateFrozenBacklog.getOperation()))
            opera="frozen";
        else
            opera="null";
        String update_flag="UPDATE account_status SET "+opera+" =? WHERE phoneNum=?";
        preStatement=DBconnecTion.connection.prepareStatement(update_flag);
        preStatement.setInt(1,status);
        preStatement.setString(2,phoneNum);
        int up=preStatement.executeUpdate();
        if(up==1){
            result=true;
        }
        else{
            System.out.println("update flag false");
        }
        return result;
    }

    /**
     * TODO:注销账户
     * @param phoneNum
     * @return
     * @throws SQLException
     */
    public static boolean delUserInAlltables(String phoneNum)throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );
        boolean result=false;
        String sql="DELETE \n" +
                "account_info,\n" +
                "account_money,\n" +
                "account_status,\n" +
                "admin_backlog \n" +
                "FROM\n" +
                "account_info LEFT JOIN account_money ON account_info.phoneNum=account_money.phoneNum\n" +
                "LEFT JOIN account_status ON account_info.phoneNum=account_status.phoneNum\n" +
                "LEFT JOIN admin_backlog ON account_info.phoneNum=admin_backlog.phoneNum\n" +
                "WHERE account_info.phoneNum=?";
        preStatement=DBconnecTion.connection.prepareStatement(sql);
        preStatement.setString(1,phoneNum);
        int res=preStatement.executeUpdate();
        if(res!=0){
            result=true;
        }
        return result;
    }

    /**
     * TODO:获取用户列表
     * @return
     * @throws SQLException
     */
    public static Vector<accountUser>listUsers()throws SQLException{
        if(!DBconnecTion.connectToDB)
            throw new SQLException( "Not Connected to Database" );

        Vector<accountUser>users= new Vector<>();
        statement=DBconnecTion.connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String sql="SELECT * FROM account_info";
        resultSet=statement.executeQuery(sql);
        while (resultSet.next()){
            String cardNumber=resultSet.getString("cardNumber");
            String passWord="null";
            String phoneNum=resultSet.getString("phoneNum");
            String userName=resultSet.getString("userName");
            Timestamp loginTime=resultSet.getTimestamp("loginTime");

           accountUser temp=new accountUser(cardNumber,passWord,userName,phoneNum,loginTime);
            users.addElement(temp);
        }
        return users;
    }

}
