package client.user;/*
@author 杨马也
@creat $(DATE)-$(TIME)
*/

import global.mes.accountUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class viewSelfMessage extends JFrame implements ActionListener {

    private JPanel ContentPanel;
    private JLabel Main_label;

    private JLabel Name_Label;
    private JLabel PhoneNum_label;
    private JLabel CardNum_label;
    private JLabel Time_label;
    private JTextField Name_Txt;
    private JTextField PhoneNum_Txt;
    private JTextField CardNum_Txt;
    private JTextField Time_Txt;
    private JButton Confirm_Button;
    private JButton Cancel_Button;
    private ImageIcon picture;
    private JLabel pictureLabel;
    private accountUser user;

    public viewSelfMessage(accountUser user) {
        this.user=user;
        this.setTitle("个人信息");
        this.setBounds(550, 250, 420, 380);
        this.setVisible(true);
        this.setContentPane(this.viewSelfMessage(user));
        Confirm_Button.addActionListener(this);
        Cancel_Button.addActionListener(this);
    }

    public JPanel viewSelfMessage(accountUser user) {
        ContentPanel = new JPanel();
        ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(ContentPanel);
        //
        picture = new ImageIcon("src/images/selfMessage.png");
        pictureLabel = new JLabel(picture);
        pictureLabel.setBounds(60, 30, 70, 70);
        Main_label = new JLabel("个人信息");
        Main_label.setBounds(140, 50, 300, 50);
        Main_label.setFont(new Font("黑体", Font.PLAIN, 45));
        //
        Name_Label = new JLabel("用户姓名：");
        PhoneNum_label = new JLabel("电话号码：");
        CardNum_label = new JLabel("银行卡号：");
        Time_label = new JLabel("注册时间：");
        //
        Name_Label.setBounds(70, 110, 180, 40);
        Name_Label.setFont(new Font("黑体", Font.PLAIN, 20));
        PhoneNum_label.setBounds(70, 150, 180, 40);
        PhoneNum_label.setFont(new Font("黑体", Font.PLAIN, 20));
        CardNum_label.setBounds(70, 190, 180, 40);
        CardNum_label.setFont(new Font("黑体", Font.PLAIN, 20));
        Time_label.setBounds(70, 230, 180, 40);
        Time_label.setFont(new Font("黑体", Font.PLAIN, 20));
        //
        Name_Txt = new JTextField();
        Name_Txt.setBounds(180, 119, 136, 24);
        PhoneNum_Txt = new JTextField();
        PhoneNum_Txt.setBounds(180, 159, 136, 24);
        PhoneNum_Txt.setColumns(10);
        CardNum_Txt = new JTextField();
        CardNum_Txt.setBounds(180, 200, 136, 24);
        CardNum_Txt.setColumns(10);
        Time_Txt = new JTextField();
        Time_Txt.setBounds(180, 240, 136, 24);
        Time_Txt.setColumns(10);
        Name_Txt.setEnabled(false);
        PhoneNum_Txt.setEnabled(false);
        CardNum_Txt.setEnabled(false);
        Time_Txt.setEnabled(false);
        //
        Name_Txt.setText(user.getUserName());
        PhoneNum_Txt.setText(user.getPhoneNum());
        CardNum_Txt.setText(user.getCardNumber());
        Time_Txt.setText(String.valueOf(user.getLoginTime()));
        //按钮
        Confirm_Button = new JButton("确认信息");
        Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Confirm_Button.setBounds(60, 288, 120, 27);
        Cancel_Button = new JButton("修改信息");
        Cancel_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Cancel_Button.setBounds(230, 288, 120, 27);
        //
        ContentPanel.setLayout(null);
        ContentPanel.add(Main_label);
        ContentPanel.add(pictureLabel);
        ContentPanel.add(Name_Txt);
        ContentPanel.add(Name_Label);
        ContentPanel.add(PhoneNum_label);
        ContentPanel.add(PhoneNum_Txt);
        ContentPanel.add(CardNum_label);
        ContentPanel.add(CardNum_Txt);
        ContentPanel.add(Time_label);
        ContentPanel.add(Time_Txt);
        ContentPanel.add(Confirm_Button);
        ContentPanel.add(Cancel_Button);
        return ContentPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Confirm_Button){
            this.dispose();
            new userMainFrame(user).setVisible(true);
        }else if(e.getSource()==Cancel_Button){
            this.dispose();
            new ChangePassword(user).setVisible(true);
        }
    }

}
