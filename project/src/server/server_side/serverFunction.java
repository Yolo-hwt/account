package server.server_side;

import global.mes.accountFlag;
import global.mes.accountUser;
import global.mes.backLog;
import global.mes.message;
import global.operation;
import server.dataBase.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

class serverFunction {
private serverConnection connection;
public serverFunction(serverConnection connect){
    this.connection=connect;
}

    /**
     * 用户功能
     *
     */
    //添加卡
    protected void addAnewCard(message mes){
        accountUser user = mes.getUser();
        accountFlag flag =mes.getFlag();
        boolean result=false;
        try {
            result=dataProcessing.insertUser(user,flag);
            if(!result){
                mes.setDescribe("开卡失败");
            } else{
                mes.getFlag().setStatusFlag("success");
                mes.setDescribe("新账户已开通");
            }
            connection.sendData(mes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //处理用户请求
    protected void apply_Account(message mes){
        accountUser user = mes.getUser();
        accountFlag flag =mes.getFlag();

        boolean result;
        try {
            result=dataProcessing.insertUserBackLog(user.getPhoneNum(),mes.getDescribe(),flag.getOperaTion());
            if(!result)
                mes.getFlag().setStatusFlag("fail");
            else{
                mes.getFlag().setStatusFlag("success");
                mes.setDescribe("server: apply has already get");
            }
            connection.sendData(mes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //存取款
    protected void depositOrWithdrawMoney(message mes){
        accountUser user = mes.getUser();
        accountFlag flag =mes.getFlag();

        boolean result=false;
        try {
            result=dataProcessing.updateMoney(user.getPhoneNum(), flag.getOperaTion(), flag.getAmount());

            if(!result)
                mes.getFlag().setStatusFlag("fail");
            else
                mes.getFlag().setStatusFlag("success");
           connection.sendData(mes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //用户登录
    protected void userLogin(message mes){
        accountUser user = mes.getUser();
        accountFlag flag =mes.getFlag();
        try {
            accountUser result=dataProcessing.searchUserByNP(user.getPhoneNum(),user.getPassWords());
            if(result!=null){
                flag.setStatusFlag("success");
                message res=new message(result,flag);
                res.setDescribe("login success");
                connection.sendData(res);
            }
            else{
                System.out.println("false to find user");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //更改个人密码
    protected void changePassword(message mes){
        accountUser user = mes.getUser();
        accountFlag flag =mes.getFlag();
        try {
            boolean result=dataProcessing.updatePassword(user.getPhoneNum(),user.getPassWords());
            if(result){
                flag.setStatusFlag("success");
                message res=new message(user,flag);
                res.setDescribe("changePassword success");
                connection.sendData(mes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**********************************************************/

    /**
     *管理员功能
     *
     */
    //管理员登陆
    protected void adminlogin(message mes){
        accountUser user=mes.getUser();
        accountFlag flag=mes.getFlag();
        accountUser result=null;
        try {
            result=dataProcessing.searchAdmin(user.getCardNumber(),user.getPassWords());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        flag.setStatusFlag("success");
        message temp=new message(result,flag);
        connection.sendData(temp);
        return;
    }
    //获取对应列表数据
    protected void getBacklogsByOpera(String operaTionName){
        Vector <backLog>result;
        Vector<backLog>logsByOpera=new Vector<>();
        accountFlag flag=new accountFlag(operaTionName,"success");
        message mes=new message(null,flag,"backlogs has already get,please receive");
        String userOpera=operation.adminToUser(operaTionName);
        try {
            result = dataProcessing.listBacklogs();
            if(result==null){
                flag.setStatusFlag("false");
                mes.setDescribe("fail to get any backlogs about "+operaTionName);
                connection.sendData(mes);
                return;
            }
            for(int i=0;i<result.size();i++){
                backLog temp= result.get(i);
                if(temp.getOperaTion().equals(userOpera))//匹配操作名(尤其注意为用户申请请求时候的操作名)
                    if(temp.getSolve()==0){//若未处理，solve==0
                        logsByOpera.add(temp);
                    }
            }
            if(logsByOpera.size()==0){
                flag.setStatusFlag("false");
                mes.setDescribe("there is no backlogs about "+operaTionName+"(user:"+userOpera+")");
                connection.sendData(mes);
                return;
            }
            connection.sendData(mes);
            connection.getOutput().writeObject(logsByOpera);
            connection.getOutput().flush();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (IOException write){
            write.printStackTrace();
        }
        return;
    }
    //获取已办列表
    protected void getFinishBacklogs() {
        Vector<backLog> result;
        Vector<backLog> finishLogs = new Vector<>();
        String finishOpera=operation.ADMIN_OPERATION_ENUM.getFinfshBackLogs.getOperation();
        accountFlag flag = new accountFlag(finishOpera, "success");
        message mes = new message(null, flag, "backlogs has already get,please receive");
        try {
            result = dataProcessing.listBacklogs();
            if (result == null) {
                flag.setStatusFlag("false");
                mes.setDescribe("fail to get any backlogs about " + finishOpera);
                connection.sendData(mes);
                return;
            }
            for(int i=0;i<result.size();i++){
                backLog temp= result.get(i);
                if(temp.getSolve()==1){//若已处理，solve==1
                    finishLogs.add(temp);
                }
            }
            connection.sendData(mes);
            connection.getOutput().writeObject(finishLogs);
            connection.getOutput().flush();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (IOException write){
            write.printStackTrace();
        }
        return;
    }
    //处理用户挂失，冻结账户
    protected void dealWithLossOrFrozen(String phoneNum,String opera){
        boolean result=false;
        boolean res1=false;
        boolean res2=false;
        try {
            accountFlag flag=new accountFlag(opera,"success");
            message message=new message(null,flag,opera+" success");
           res1=dataProcessing.updateflag(phoneNum,opera,1);//更新对应状态
            if(res1){
                res2=dataProcessing.updateBacklogs(phoneNum,opera,opera+"已处理");//更新backlog列表
                if(res2) {
                    connection.sendData(message);
                    return;
                }
                else{
                    System.out.println(" fail to updateBacklogs\nphone:"+phoneNum+"\noperation:"+opera);
                }
            }
            else{
                System.out.println(" fail to updateflag\nphone:"+phoneNum+"\noperation:"+opera);
            }
            message.getFlag().setStatusFlag("false");
            connection.sendData(message);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return;
    }
    //删除用户
    protected void delUser(String phoneNum){
        accountFlag flag=new accountFlag("delUser","success");
        message message=new message(null,flag,phoneNum+" has already delete success");
        try {
           boolean result=dataProcessing.delUserInAlltables(phoneNum);
           if(result){
               connection.sendData(message);
               return;
           }
           else{
               flag.setStatusFlag("false");
               message.setDescribe(phoneNum+" fail to delete");
               connection.sendData(message);
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return;
    }
    //获取所有用户
    protected void getUsers(){
        try {
            Vector<accountUser>temp=dataProcessing.listUsers();
            accountFlag flag=new accountFlag(operation.ADMIN_OPERATION_ENUM.getAllUser.getOperation(),"success");
            message mes=new message(null,flag,"userlist has already get,please receive");
            if(temp!=null){
                connection.sendData(mes);

                connection.getOutput().writeObject(temp);
                connection.getOutput().flush();
            }
            else{
                flag.setStatusFlag("false");
                mes.setDescribe("fail to get users");
                connection.sendData(mes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    //
}
