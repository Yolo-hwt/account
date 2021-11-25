package server.server_side;

import global.mes.*;

public class server_AdminProcess {
    private serverFunction func;
    public server_AdminProcess(serverConnection connection1){
        func=new serverFunction(connection1);
    }
    protected void processConnection(message mesfromAdmin){
        switch (global.operation.ADMIN_OPERATION_ENUM.valueOf(mesfromAdmin.getFlag().getOperaTion())){
            case adminLogin://管理员登录
                func.adminlogin(mesfromAdmin);
                break;
                //获取对应待办列表
            case getLossBacklogs:
            case getFrozenBacklogs:
            case getCloseBacklogs:
                func.getBacklogsByOpera(mesfromAdmin.getFlag().getOperaTion());
                break;
                //获取已办列表
            case getFinfshBackLogs:
                func.getFinishBacklogs();
                break;
                //更新用户状态
            case updateFrozenBacklog:
            case updateLossBacklog:
                func.dealWithLossOrFrozen(mesfromAdmin.getUser().getPhoneNum(),mesfromAdmin.getFlag().getOperaTion());
                break;
                //注销卡
            case delCard:
                func.delUser(mesfromAdmin.getUser().getPhoneNum());
                break;
                //获取所有用户
            case getAllUser:
                func.getUsers();
                break;
        }
    }
}
