package client.frameProcess;

import client.client_side;
import client.user.*;
import global.MainFrame;
import global.mes.*;
import global.operation;

import javax.swing.*;

public class changePasswordProcess {
    public static void changeCancel(ChangePassword cancel, accountUser user) {
        JOptionPane.showMessageDialog(null, "成功取消！");
        cancel.dispose();
        new userMainFrame(user).setVisible(true);
    }

    public static void changeConfirm(ChangePassword confirm, accountUser user) {
        String Password = String.valueOf(confirm.getPassword_Txt().getPassword());
        String password_1 = String.valueOf(confirm.getPassword_Txt1().getPassword());
        String password_2 = String.valueOf(confirm.getPassword_Txt2().getPassword());

        if (confirm.password.isEmpty() || password_1.isEmpty() || password_2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "输入的密码不能为空！");
            return;
        }
        if (!confirm.password.equals(Password)) {
            confirm.ClearText();
            JOptionPane.showMessageDialog(null, "原密码输入错误，请重新输入");
            return;
        }
        if (Password.equals(password_1)) {
            confirm.ClearText();
            JOptionPane.showMessageDialog(null, "新旧密码相同，请重新输入");
            return;
        }
        if (!password_1.equals(password_2)) {
            confirm.ClearText();
            JOptionPane.showMessageDialog(null, "新密码确认失败，两次输入不符，请重新输入");
            return;
        }

        String opera = operation.USER_OPERATION_ENUM.changePassword.getOperation();
        String sta = "false";
        accountFlag flag = new accountFlag(opera, sta);
        user.setPassWords(password_1);
        message mes = new message(user, flag);
        client_side.Myclient.sendData(mes);

        message res = client_side.Myclient.getData();
        if (res.getFlag().getStatusFlag().equals("success")) {
            JOptionPane.showMessageDialog(null, "修改成功！请重新登录");
            confirm.dispose();
            new MainFrame().setVisible(true);
        }

    }
}
