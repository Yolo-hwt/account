package client.user;

import client.frameProcess.changePasswordProcess;
import client.frameProcess.depositOrWithdrawProcess;
import global.mes.*;
import global.operation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class depositOrWithdraw extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JLabel Main_Label;
    private JLabel OperaTion_Label;
    private JLabel MoneyAmount_Label;
    private JTextField OperaTion_Txt;
    private JTextField MoneyAmount_Txt;
    private JButton Confirm_Button;
    private JButton Cancel_Button;
    private ImageIcon picture;
    private JLabel picture_Label;
    private JLabel picture1;
    private JLabel picture2;

    private accountFlag myflag;
    private accountUser user;
    public depositOrWithdraw(message mes) {
        this.myflag=mes.getFlag();
        this.user=mes.getUser();
        this.setTitle("ATM");
        this.setResizable(false);
        this.setContentPane(this.moneyFrame());
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 250, 565, 372);
        Confirm_Button.addActionListener(this);
        Cancel_Button.addActionListener(this);
    }

    /*public static void main(String[] args) {
        accountFlag flag=new accountFlag(operation.USER_OPERATION_ENUM.depositMoney.getOperation(),"false");
        new depositOrWithdraw().setVisible(true);
    }*/
    public JPanel moneyFrame() {
        // 中间容器
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        //主标签
        String filename="src/images/withdraw.png" ;
        String opera="取款";
        if(myflag.getOperaTion().equals(operation.USER_OPERATION_ENUM.depositMoney.getOperation())){
            filename="src/images/deposit.png" ;
            opera="存款";
        }


        picture = new ImageIcon(filename);
        picture_Label = new JLabel(picture);
        picture_Label.setBounds(240, 3, 80, 80);
        Main_Label = new JLabel("ATM自助存取款");
        Main_Label.setBounds(110, 50, 500, 100);
        Main_Label.setFont(new Font("黑体", Font.PLAIN, 45));
        // 操作标签
        picture = new ImageIcon("src/images/userName.png");
        picture1 = new JLabel(picture);
        picture1.setBounds(100, 157, 30, 20);
        OperaTion_Label = new JLabel("操  作：");
        OperaTion_Label.setBounds(128, 149, 90, 40);
        OperaTion_Label.setFont(new Font("黑体", Font.PLAIN, 20));

        // 金额标签
        picture = new ImageIcon("src/images/amount.png");
        picture2 = new JLabel(picture);
        picture2.setBounds(100, 210, 30, 20);
        MoneyAmount_Label = new JLabel("金  额：");
        MoneyAmount_Label.setBounds(128, 202, 90, 40);
        MoneyAmount_Label.setFont(new Font("黑体", Font.PLAIN, 20));

        // 文本域
        OperaTion_Txt = new JTextField();
        OperaTion_Txt.setBounds(222, 160, 176, 24);
        OperaTion_Txt.setColumns(10);
        OperaTion_Txt.setEnabled(false);
        OperaTion_Txt.setText(opera);
        OperaTion_Txt.setFont(new Font("黑体", Font.PLAIN, 20));


        MoneyAmount_Txt = new JTextField();
        MoneyAmount_Txt.setBounds(222, 210, 176, 24);

        // 确认按钮
        Confirm_Button = new JButton("确认");
        Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Confirm_Button.setBounds(140, 276, 85, 27);

        // 取消按钮
        Cancel_Button = new JButton("取消");
        Cancel_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Cancel_Button.setBounds(313, 276, 85, 27);

        //设置布局 添加部件
        contentPane.setLayout(null);
        contentPane.add(Main_Label);
        contentPane.add(picture_Label);
        contentPane.add(OperaTion_Label);
        contentPane.add(picture1);
        contentPane.add(MoneyAmount_Label);
        contentPane.add(picture2);
        contentPane.add(OperaTion_Txt);
        contentPane.add(MoneyAmount_Txt);
        contentPane.add(Confirm_Button);
        contentPane.add(Cancel_Button);
        return contentPane;
    }

    public JTextField getOperaTion_Txt() {
        return OperaTion_Txt;
    }

    public JTextField getMoneyAmount_Txt() {
        return MoneyAmount_Txt;
    }
    public void Clear(){
        this.getMoneyAmount_Txt().setText("");
    }
    public void actionPerformed(ActionEvent e) {
        JButton temp = (JButton) e.getSource();
        if (temp == Cancel_Button) {
            depositOrWithdrawProcess.moneyCancel(this,user);
        }
        if (temp == Confirm_Button) {
            message confirmMes=new message(this.user,this.myflag);
            depositOrWithdrawProcess.moneyConfirm(this,confirmMes);
        }
    }
}
