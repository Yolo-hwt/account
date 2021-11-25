package client.user;/*
@author 杨马也
@creat $(DATE)-$(TIME)
*/

import global.mes.accountUser;
import client.frameProcess.changePasswordProcess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChangePassword extends JFrame implements ActionListener {
    private JPanel contentPanel;
    private JLabel Main_label;
    private JPasswordField Password_Txt;
    private JPasswordField Password_Txt1;
    private JPasswordField Password_Txt2;
    private JLabel Password_Label;
    private JLabel Password_Label1;
    private JLabel Password_Label2;
    private JButton Confirm_Button;
    private JButton Cancel_Button;
    public static String password;
    private ImageIcon picture;
    private JLabel pictureLabel;
    private accountUser user;

    public ChangePassword(accountUser user) {
        password = user.getPassWords();
        this.user = user;
        this.setTitle("修改密码");
        this.setContentPane(this.ChangeMessage());
        this.setBounds(550, 250, 450, 420);
        this.setVisible(true);
        Confirm_Button.addActionListener(this);
        Cancel_Button.addActionListener(this);
    }

    public JPanel ChangeMessage() {
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        //
        picture = new ImageIcon("src/images/changeMessage.png");
        pictureLabel = new JLabel(picture);
        pictureLabel.setBounds(80, 50, 60, 60);
        Main_label = new JLabel("修改密码");
        Main_label.setBounds(140, 50, 300, 50);
        Main_label.setFont(new Font("黑体", Font.PLAIN, 45));
        //
        Password_Label = new JLabel("输入原密码：");
        Password_Label1 = new JLabel("输入新密码：");
        Password_Label2 = new JLabel("请确认密码：");
        Password_Label.setBounds(50, 150, 180, 40);
        Password_Label.setFont(new Font("黑体", Font.PLAIN, 20));
        Password_Label1.setBounds(50, 199, 180, 40);
        Password_Label1.setFont(new Font("黑体", Font.PLAIN, 20));
        Password_Label2.setBounds(50, 252, 180, 40);
        Password_Label2.setFont(new Font("黑体", Font.PLAIN, 20));
        //
        Password_Txt = new JPasswordField();
        Password_Txt.setBounds(180, 159, 136, 24);
        Password_Txt1 = new JPasswordField();
        Password_Txt1.setBounds(180, 209, 136, 24);
        Password_Txt1.setColumns(10);
        Password_Txt2 = new JPasswordField();
        Password_Txt2.setBounds(180, 260, 136, 24);
        Password_Txt2.setColumns(10);
        // 确认按钮
        Confirm_Button = new JButton("确认");
        Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Confirm_Button.setBounds(110, 320, 85, 27);
        // 取消按钮
        Cancel_Button = new JButton("取消");
        Cancel_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Cancel_Button.setBounds(230, 320, 85, 27);
        //
        contentPanel.setLayout(null);
        contentPanel.add(Main_label);
        contentPanel.add(pictureLabel);
        contentPanel.add(Password_Label);
        contentPanel.add(Password_Label1);
        contentPanel.add(Password_Label2);
        contentPanel.add(Password_Txt);
        contentPanel.add(Password_Txt1);
        contentPanel.add(Password_Txt2);
        contentPanel.add(Confirm_Button);
        contentPanel.add(Cancel_Button);
        return contentPanel;
    }

    public void actionPerformed(ActionEvent e) {
        JButton temp = (JButton) e.getSource();
        if (temp == Cancel_Button) {
            changePasswordProcess.changeCancel(this, user);
        }
        if (temp == Confirm_Button) {
            changePasswordProcess.changeConfirm(this, user);
        }
    }

    //清空文本框
    public void ClearText() {
        Password_Txt.setText("");
        Password_Txt1.setText("");
        Password_Txt2.setText("");
    }

    public JPasswordField getPassword_Txt() {
        return Password_Txt;
    }

    public JPasswordField getPassword_Txt1() {
        return Password_Txt1;
    }

    public JPasswordField getPassword_Txt2() {
        return Password_Txt2;
    }
}