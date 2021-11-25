package client.frameProcess;

import client.client_side;
import client.user.depositOrWithdraw;
import client.user.userMainFrame;
import global.mes.*;
import global.operation;

import javax.swing.*;

public class depositOrWithdrawProcess {
    public static void moneyCancel(depositOrWithdraw cancel, accountUser user){
        JOptionPane.showMessageDialog(null, "成功取消！");
        cancel.dispose();
        new userMainFrame(user).setVisible(true);
    }
    public static void moneyConfirm(depositOrWithdraw confirm,message mes){
        Double amount=Double.valueOf(confirm.getMoneyAmount_Txt().getText());
        String opera= operation.USER_OPERATION_ENUM.depositMoney.getOperation();
        if(confirm.getOperaTion_Txt().getText().equals("取款")){
            opera=operation.USER_OPERATION_ENUM.withdrawMoney.getOperation();
        }
        mes.getFlag().setStatusFlag("false");
        mes.getFlag().setOperaTion(opera);
        mes.getFlag().setAmount(amount);
        client_side.Myclient.sendData(mes);
        message result=client_side.Myclient.getData();
           if(result.getFlag().getStatusFlag().equals("success")){
               JOptionPane.showMessageDialog(null,"存款成功！");
               confirm.Clear();
               new userMainFrame(mes.getUser()).setVisible(true);
               confirm.dispose();
           }
           else{
               JOptionPane.showMessageDialog(null,"操作失败");
               confirm.Clear();
           }
    }
}
