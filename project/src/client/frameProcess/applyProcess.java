package client.frameProcess;

import client.client_side;
import client.user.applyOperation;
import client.user.userMainFrame;
import global.mes.accountUser;
import global.mes.message;
import global.operation;

import javax.swing.*;

public class applyProcess {
    public static void applyConfirm(applyOperation confirm, message mes){
        if(!mes.getUser().getPassWords().equals(confirm.getConfirmPassword())){
            System.out.println("txt:"+confirm.getConfirmPassword());
            System.out.println("user"+mes.getUser().getPassWords());
            JOptionPane.showMessageDialog(null, "密码输入错误！请重新确认！");
            confirm.Clear();
            return;
        }
        //销户确认
        if(mes.getFlag().getOperaTion().equals(operation.USER_OPERATION_ENUM.closeAccount.getOperation())){
            String userName=mes.getUser().getUserName();
            String CardN=mes.getUser().getCardNumber();
            // 显示确认界面：信息，标题，选项个数
            int value = JOptionPane.showConfirmDialog
                    (null,
                            "确定删除账户\n"+"用户名: "+userName+"\n账户: "+CardN
                            , "close account?", 2);
            // Yes=0 No=1
            if(value==1){//取消
                confirm.Clear();
                return;
            }
        }
        mes.setDescribe("user_apply");
        client_side.Myclient.sendData(mes);
        message getmes=client_side.Myclient.getData();
        if(getmes.getFlag().getStatusFlag().equals("success")){
            JOptionPane.showMessageDialog(null, "当前账户申请已提交，等待管理员处理...");
        }
        else{
            JOptionPane.showMessageDialog(null, "提交状态异常！！！");
        }
        new userMainFrame(mes.getUser()).setVisible(true);
        confirm.dispose();
    }
    public static void applyCancel(applyOperation cancel, accountUser user){
        JOptionPane.showMessageDialog(null, "申请取消");
        cancel.dispose();
        new userMainFrame(user).setVisible(true);
    }

}
