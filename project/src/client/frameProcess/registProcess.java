package client.frameProcess;

import global.MainFrame;
import client.client_side;
import client.mainGUI.RegistFrame;
import client.mainGUI.UserLoginFrame;
import global.mes.*;
import global.operation;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Random;

public class registProcess{

    public static void registConfirm(RegistFrame confirm) {
        String userName = confirm.getName_Txt().getText();
        String phoneNum = confirm.getDel_Txt().getText();//手机号
        String passWord1 = String.valueOf(confirm.getPassword_Txt1().getPassword());
        String passWord2 = String.valueOf(confirm.getPassword_Txt2().getPassword());

        if (userName == "" | phoneNum == "" | passWord1 == "" | passWord2 == "") {
            JOptionPane.showMessageDialog(null, "您有未填写的信息！");
        } else if (!passWord1.equals(passWord2)) {
            JOptionPane.showMessageDialog(null, "两次密码不一致！");
        } else {
            int temp = (int) ((Math.random() * 9 + 1) * 100000000);
            String cardNum1 = String.valueOf(temp);
            temp = (int) ((Math.random() * 9 + 1) * 10000000);

            Random random = new Random();
            String cardNum="";
            for (int i=0;i<6;i++)
                cardNum+=random.nextInt(10);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            accountUser user = new accountUser(cardNum, passWord1, userName, phoneNum, timestamp);

            String opera = operation.USER_OPERATION_ENUM.addUser.getOperation();
            String sta = "false";
            accountFlag flag = new accountFlag(opera, sta);
            flag.setAmount(10.0);//新开卡充值10元

            message mes = new message(user, flag, "apply to create a new account");
            //
            client_side.Myclient.sendData(mes);
            mes = client.client_side.Myclient.getData();
            if (mes.getFlag().getStatusFlag().equals("success")) {
                JOptionPane.showMessageDialog(null, "开卡成功！请登录");
                confirm.dispose();
                new UserLoginFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "该用户已存在！请直接登录");
            }
        }
    }

    public static void registCancel(RegistFrame cancel){
        JOptionPane.showMessageDialog(null, "成功取消！");
        cancel.dispose();
        new MainFrame().setVisible(true);
    }
}
