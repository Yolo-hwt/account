package client.mainGUI;/*
@author 杨马也
@creat $(DATE)-$(TIME)
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.frameProcess.registProcess;

public class RegistFrame extends JFrame implements ActionListener {

    private JPanel  ContentPanel;
    private JLabel  Main_label;
    private JLabel  Name_Label;
    private JLabel  Del_Label;
    private JLabel Password_Label1;
    private JLabel Password_Label2;
    private JTextField Name_Txt;
    private JTextField Del_Txt;
    private JPasswordField Password_Txt1;
    private JPasswordField Password_Txt2;
    private ImageIcon picture;
    private JLabel picture_Label;
    private JButton Confirm_Button;
    private JButton Cancel_Button;
    private JLabel picture1;
    private JLabel picture2;
    private JLabel picture3;
    private JLabel picture4;

    public RegistFrame(){
        this.setTitle("注册开卡");
        this.setBounds(550, 250, 500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.RegistFrame());
        Confirm_Button.addActionListener(this);
        Cancel_Button.addActionListener(this);
    }
    public JPanel RegistFrame(){
        //主面板
        ContentPanel = new JPanel();
        ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(ContentPanel);
        //主标签
        picture = new ImageIcon("src/images/regist.png");
        picture_Label = new JLabel(picture);
        picture_Label.setBounds(100, 20, 70, 70);
        Main_label = new JLabel("注册开卡");
        Main_label.setBounds(180, 40, 300, 50);
        Main_label.setFont(new Font("黑体", Font.PLAIN, 45));
        //用户名
        picture = new ImageIcon("src/images/y1.png");
        picture1 = new JLabel(picture);
        picture1.setBounds(85,112,32,32);
        Name_Label = new JLabel("用户姓名：");
        Name_Label.setBounds(130, 110, 180, 40);
        Name_Label.setFont(new Font("黑体", Font.PLAIN, 20));
        Name_Txt = new JTextField();
        Name_Txt.setBounds(240, 119, 130, 24);
        //电话号
        picture = new ImageIcon("src/images/y2.png");
        picture2 = new JLabel(picture);
        picture2.setBounds(85,152,32,32);
        Del_Label = new JLabel("电话号码：");
        Del_Label.setBounds(130, 150, 180, 40);
        Del_Label.setFont(new Font("黑体", Font.PLAIN, 20));
        Del_Txt = new JTextField();
        Del_Txt.setBounds(240, 159, 130, 24);
        //密码框
        picture = new ImageIcon("src/images/y3.png");
        picture3 = new JLabel(picture);
        picture3.setBounds(85,192,32,32);
        picture = new ImageIcon("src/images/y4.png");
        picture4 = new JLabel(picture);
        picture4.setBounds(85,232,32,32);
        Password_Label1 = new JLabel("设置密码：");
        Password_Label2 = new JLabel("确认密码：");
        Password_Label1.setBounds(130, 190, 180, 40);
        Password_Label1.setFont(new Font("黑体", Font.PLAIN, 20));
        Password_Label2.setBounds(130, 230, 180, 40);
        Password_Label2.setFont(new Font("黑体", Font.PLAIN, 20));
        Password_Txt1 = new JPasswordField();
        Password_Txt1.setBounds(240, 199, 130, 24);
        Password_Txt1.setColumns(10);
        Password_Txt2 = new JPasswordField();
        Password_Txt2.setBounds(240, 240, 130, 24);
        Password_Txt2.setColumns(10);
        //确认按钮
        Confirm_Button = new JButton("确认");
        Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Confirm_Button.setBounds(90, 300, 85, 27);
        //取消按钮
        Cancel_Button = new JButton("取消");
        Cancel_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Cancel_Button.setBounds(284, 300, 85, 27);
        //添加控件
        ContentPanel.setLayout(null);
        ContentPanel.add(Main_label);
        ContentPanel.add(picture_Label);
        ContentPanel.add(Name_Txt);
        ContentPanel.add(Name_Label);
        ContentPanel.add(Del_Txt);
        ContentPanel.add(Del_Label);
        ContentPanel.add(Password_Label1);
        ContentPanel.add(Password_Label2);
        ContentPanel.add(Password_Txt1);
        ContentPanel.add(Password_Txt2);
        ContentPanel.add(Confirm_Button);
        ContentPanel.add(Cancel_Button);
        ContentPanel.add(picture1);
        ContentPanel.add(picture2);
        ContentPanel.add(picture3);
        ContentPanel.add(picture4);
        return ContentPanel;
    }
    public void actionPerformed(ActionEvent e){
        JButton temp=(JButton)e.getSource();
        if(temp==Confirm_Button){
            registProcess.registConfirm(this);
        }
        if(temp==Cancel_Button){
           registProcess.registCancel(this);
        }
    }

    public JTextField getDel_Txt() {
        return Del_Txt;
    }

    public JTextField getName_Txt() {
        return Name_Txt;
    }

    public JPasswordField getPassword_Txt1() {
        return Password_Txt1;
    }

    public JPasswordField getPassword_Txt2() {
        return Password_Txt2;
    }
}
