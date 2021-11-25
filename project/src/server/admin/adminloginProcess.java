package server.admin;

import global.MainFrame;
import global.mes.*;
import global.operation;
import javax.swing.*;
import client.client_side;
public class adminloginProcess {

    public static void adminloginConfirm(AdminLoginFrame confirm){
        String  adminNum=confirm.getUsername_Txt().getText();
        String  adminPass=String.valueOf(confirm.getPassword_Txt().getPassword());
        if(adminNum.equals("")|adminPass.equals("")){
            JOptionPane.showMessageDialog(null,"您有未填写的信息！");
        }else{
            String opera= operation.ADMIN_OPERATION_ENUM.adminLogin.getOperation();
            String sta="false";
            accountFlag flag= new accountFlag(opera,sta);
            accountUser user=new accountUser(adminNum,adminPass);
            //user.setPhoneNum(adminNum);
            message message=new message(user,flag,"admin apply to login");
            client_side.Myclient.sendData(message);
            message=client_side.Myclient.getData();
            if(message.getFlag().getStatusFlag().equals("success")){
                JOptionPane.showMessageDialog(null,"管理员登陆成功");
                confirm.dispose();
                new adminMainFrame().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"账号或密码错误 请重新输入！");
            }
        }
    }
    public static void adminloginCancel(AdminLoginFrame cancel){
        JOptionPane.showMessageDialog(null, "成功取消！");
        cancel.dispose();
        new MainFrame().setVisible(true);
    }
}
