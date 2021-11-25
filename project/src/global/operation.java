package global;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class operation {
    private  Enumeration<USER_OPERATION_ENUM> userOperas;
    private Enumeration<ADMIN_OPERATION_ENUM> adminOperas;

    public operation() {
        Vector<USER_OPERATION_ENUM> users=new Vector<USER_OPERATION_ENUM>();
        users.add(USER_OPERATION_ENUM.addUser);
        users.add(USER_OPERATION_ENUM.changePassword);
        users.add(USER_OPERATION_ENUM.closeAccount);
        users.add(USER_OPERATION_ENUM.frozenAccount);
        users.add(USER_OPERATION_ENUM.lossCard);
        users.add(USER_OPERATION_ENUM.depositMoney);
        users.add(USER_OPERATION_ENUM.withdrawMoney);
        users.add(USER_OPERATION_ENUM.userLogin);
        this.userOperas= users.elements();
    }

    public  Enumeration<USER_OPERATION_ENUM> getUserOperas() {
        return userOperas;
    }

    //用户操作集
    public static enum USER_OPERATION_ENUM{
        addUser("addUser"),
        closeAccount("closeAccount"),
        frozenAccount("frozenAccount"),
        lossCard("lossCard"),
        changePassword("changePassword"),
        depositMoney("depositMoney"),
        withdrawMoney("withdrawMoney"),
        userLogin("userLogin")
        ;

        private String operation;
        USER_OPERATION_ENUM(String operation){ this.operation=operation; }
        public String getOperation() { return operation; }
    }

    //管理员操作集
    public static enum ADMIN_OPERATION_ENUM {
        getFrozenBacklogs("getFrozenBacklogs"),
        getLossBacklogs("getLossBacklogs"),
        getCloseBacklogs("getCloseBacklogs"),
        updateLossBacklog("updateLossBacklog"),
        updateFrozenBacklog("updateFrozenBacklog"),
        delCard("delCard"),
        adminLogin("adminLogin"),
        getAllUser("getAllUser"),
        getFinfshBackLogs("getFinfshBackLogs")
        ;
        private String operation;
        ADMIN_OPERATION_ENUM(String operation){ this.operation=operation; }
        public String getOperation() { return operation; }
    }

    /**提示窗口（全局）
     *
     * @param component
     * @param msg
     * @param title
     */
    public static void showMessage(Component component, String msg, String title) {
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.YES_NO_OPTION);
    }

    /**
     * TODO:转化用户管理员操作
     * @param adminOpera
     * @return
     */
    public static String adminToUser(String adminOpera){
        String userOpera=null;
        if(adminOpera.equals(operation.ADMIN_OPERATION_ENUM.updateLossBacklog.getOperation())||
                adminOpera.equals(ADMIN_OPERATION_ENUM.getLossBacklogs.getOperation()))
            userOpera=USER_OPERATION_ENUM.lossCard.getOperation();

        if(adminOpera.equals(ADMIN_OPERATION_ENUM.updateFrozenBacklog.getOperation())||
                adminOpera.equals(ADMIN_OPERATION_ENUM.getFrozenBacklogs.getOperation()))
            userOpera=USER_OPERATION_ENUM.frozenAccount.getOperation();

        if(adminOpera.equals(ADMIN_OPERATION_ENUM.delCard.getOperation())||
                adminOpera.equals(ADMIN_OPERATION_ENUM.getCloseBacklogs.getOperation()))
            userOpera=USER_OPERATION_ENUM.closeAccount.getOperation();

        return userOpera;
    }
    public static String userToAdmin(String userOpera){
        String adminOpera=null;
        if(userOpera.equals(USER_OPERATION_ENUM.lossCard.getOperation()))
            adminOpera=ADMIN_OPERATION_ENUM.updateLossBacklog.getOperation();

        if(userOpera.equals(USER_OPERATION_ENUM.frozenAccount.getOperation()))
            adminOpera=ADMIN_OPERATION_ENUM.updateFrozenBacklog.getOperation();

        if(userOpera.equals(USER_OPERATION_ENUM.closeAccount.getOperation()))
            adminOpera=ADMIN_OPERATION_ENUM.delCard.getOperation();

        return adminOpera;
    }



}
