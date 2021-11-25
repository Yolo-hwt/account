package client.frameProcess;

import global.MainFrame;
import client.client_side;
import client.user.*;
import client.mainGUI.UserLoginFrame;
import global.mes.*;
import global.operation;

import javax.swing.*;

public class userloginProcess {
    public static void userLoginCancel(UserLoginFrame cancel) {
        JOptionPane.showMessageDialog(null, "成功取消登录！");
        cancel.dispose();
        new MainFrame().setVisible(true);
    }

    public static void userLoginConfrim(UserLoginFrame confrim) {
        String PhoneNum = confrim.getPhoneNum_Txt().getText();
        String userPass = String.valueOf(confrim.getPassword_Txt().getPassword());
        if (PhoneNum.equals("") || userPass.equals("")) {
            JOptionPane.showMessageDialog(null, "您有未填写的信息！");
        } else {
            String opera = operation.USER_OPERATION_ENUM.userLogin.getOperation();
            String sta = "false";
            accountFlag flag = new accountFlag(opera, sta);
            accountUser user = new accountUser(PhoneNum, userPass);
            user.setPhoneNum(PhoneNum);
            message mes = new message(user, flag, "user apply to login");
            //请求登录
            client_side.Myclient.sendData(mes);
            mes = client_side.Myclient.getData();
            if (mes.getFlag().getStatusFlag().equals("success")) {
                JOptionPane.showMessageDialog(null, "用户登陆成功");
                confrim.dispose();
                new userMainFrame(mes.getUser()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "账号或密码错误 请重新输入！");
            }
        }
    }
}
