package server.admin;/*
@author 杨马也
@creat $(DATE)-$(TIME)
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginFrame extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel Main_Label;
    private JLabel Username_Label;
    private JLabel Password_Label;
    private JTextField Username_Txt;
    private JPasswordField Password_Txt;
    private JButton Confirm_Button;
    private JButton Cancel_Button;
    private ImageIcon picture;
    private JLabel picture_Label;
    private JLabel picture1;
    private JLabel picture2;

    public AdminLoginFrame() {
        this.setTitle("管理员登录");
        this.setResizable(false);
        this.setContentPane(this.LoginFrame());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 250, 565, 372);
        Confirm_Button.addActionListener(this);
        Cancel_Button.addActionListener(this);
    }

    public JPanel LoginFrame() {

        // 中间容器
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        //主标签
        picture = new ImageIcon("src/images/admin.png");
        picture_Label = new JLabel(picture);
        picture_Label.setBounds(240, 3, 80, 80);
        Main_Label = new JLabel("欢迎使用银行管理员系统");
        Main_Label.setBounds(40, 50, 500, 100);
        Main_Label.setFont(new Font("黑体", Font.PLAIN, 45));
        // 用户名标签
        picture = new ImageIcon("src/images/userName.png");
        picture1 = new JLabel(picture);
        picture1.setBounds(100, 157, 30, 20);
        Username_Label = new JLabel("帐  号：");
        Username_Label.setBounds(128, 149, 90, 40);
        Username_Label.setFont(new Font("黑体", Font.PLAIN, 20));

        // 密码标签
        picture = new ImageIcon("src/images/password.png");
        picture2 = new JLabel(picture);
        picture2.setBounds(100, 210, 30, 20);
        Password_Label = new JLabel("密  码：");
        Password_Label.setBounds(128, 202, 90, 40);
        Password_Label.setFont(new Font("黑体", Font.PLAIN, 20));

        // 用户名文本域
        Username_Txt = new JTextField();
        Username_Txt.setBounds(222, 160, 176, 24);
        Username_Txt.setColumns(10);

        // 密码文本域
        Password_Txt = new JPasswordField();
        Password_Txt.setBounds(222, 210, 176, 24);

        // 确认按钮
        Confirm_Button = new JButton("登录");
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
        contentPane.add(Username_Label);
        contentPane.add(picture1);
        contentPane.add(Password_Label);
        contentPane.add(picture2);
        contentPane.add(Username_Txt);
        contentPane.add(Password_Txt);
        contentPane.add(Confirm_Button);
        contentPane.add(Cancel_Button);
        return contentPane;
    }
    public void actionPerformed(ActionEvent e){
        JButton temp=(JButton)e.getSource();
        if(temp==Cancel_Button){
            adminloginProcess.adminloginCancel(this);
        }
        if(temp==Confirm_Button){
            adminloginProcess.adminloginConfirm(this);
        }
    }

    public JTextField getUsername_Txt() {
        return Username_Txt;
    }

    public JPasswordField getPassword_Txt() {
        return Password_Txt;
    }
}