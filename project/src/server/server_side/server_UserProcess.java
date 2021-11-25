package server.server_side;

import global.mes.message;

class server_UserProcess {
    /**
     *  volatile修饰符用来保证其它线程读取的总是该变量的最新的值
     *  程序连接标识
     */
    private volatile boolean exit_user;
    private serverConnection connection;
    private serverFunction func;
    public server_UserProcess(serverConnection connection1){
        this.exit_user =true;
        this.connection=connection1;
        func=new serverFunction(connection1);
    }

    public void setExit_user(boolean exit_user) {
        this.exit_user = exit_user;
    }
    protected void processConnection(message mesfromUser){

       //while(exit_user){
           switch (global.operation.USER_OPERATION_ENUM.valueOf(mesfromUser.getFlag().getOperaTion())){
               //用户登录
               case userLogin:
                   func.userLogin(mesfromUser);
                   break;
               //添加用户
               case addUser:
                   func.addAnewCard(mesfromUser);
                   break;
               case lossCard://申请挂失卡
               case frozenAccount://申请冻结账户
               case closeAccount: //申请注销账户
                   func.apply_Account(mesfromUser);
                   break;
               case depositMoney://存款
               case withdrawMoney: //取款
                   func.depositOrWithdrawMoney(mesfromUser);
                   break;
               //更改密码
               case changePassword:
                   func.changePassword(mesfromUser);
                   break;
           }
      // }
    }
}
