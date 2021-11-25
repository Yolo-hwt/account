package global;/*
@author 杨马也
@creat $(DATE)-$(TIME)
*/
import client.mainGUI.*;
import server.admin.AdminLoginFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private JPanel  contenPane;
    private ImageIcon   picture;
    private JLabel picture_Label;
    private JLabel Main_Label;
    private JButton User_Button;
    private JButton Admin_Button;
    private JButton Message_Button;
    private JButton Regist_Button;
    private JLabel picture_1;
    private JLabel picture_2;
    private JLabel picture_3;
    private JLabel picture_4;

    public MainFrame(){
        this.setTitle("银行应用系统");
        this.setResizable(false);
        this.setContentPane(this.MainFrame());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500,250,700,420);
        User_Button.addActionListener(this);
        Admin_Button.addActionListener(this);
        Message_Button.addActionListener(this);
        Regist_Button.addActionListener(this);
    }
    public JPanel MainFrame(){
        //中间容器
        contenPane=new JPanel();
        contenPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contenPane);
        //主图片
        picture=new ImageIcon("src/images/main.jpg");
        picture_Label=new JLabel(picture);
        picture_Label.setBounds(278,30,135,135);
        //主标签
        Main_Label=new JLabel("欢迎使用  银行 应用系统");
        Main_Label.setBounds(90,65,520,100);
        Main_Label.setFont(new Font("黑体", Font.PLAIN,45));
        //用户登录
        User_Button = new JButton("用户登录");
        User_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        User_Button.setBounds(130, 207, 150, 35);
        //管理员登录
        Admin_Button = new JButton("管理员登录");
        Admin_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Admin_Button.setBounds(440, 207, 150, 35);
        //银行概况了解
        Message_Button = new JButton("银行概况");
        Message_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Message_Button.setBounds(130, 272, 150, 35);
        //注册开卡
        Regist_Button = new JButton("注册开卡");
        Regist_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Regist_Button.setBounds(440, 272, 150, 35);
        //添加小图标
        picture=new ImageIcon("src/images/x1.png");
        picture_1=new JLabel(picture);
        picture_1.setBounds(80,200,48,48);
        picture=new ImageIcon("src/images/x2.png");
        picture_2=new JLabel(picture);
        picture_2.setBounds(390,200,48,48);
        picture=new ImageIcon("src/images/x3.png");
        picture_3=new JLabel(picture);
        picture_3.setBounds(80,265,48,48);
        picture=new ImageIcon("src/images/x4.png");
        picture_4=new JLabel(picture);
        picture_4.setBounds(390,265,48,48);
        //向面板添加组件
        contenPane.setLayout(null);
        contenPane.add(picture_Label);
        contenPane.add(Main_Label);
        contenPane.add(User_Button);
        contenPane.add(Admin_Button);
        contenPane.add(Message_Button);
        contenPane.add(Regist_Button);
        contenPane.add(picture_1);
        contenPane.add(picture_2);
        contenPane.add(picture_3);
        contenPane.add(picture_4);
        return contenPane;
    }
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==User_Button){
            new UserLoginFrame().setVisible(true);
            this.dispose();
        }else if(e.getSource()==Admin_Button){
            new AdminLoginFrame().setVisible(true);
            this.dispose();
        }else if(e.getSource()==Message_Button){
            new MessageFrame().setVisible(true);
            this.dispose();
        }else if(e.getSource()==Regist_Button){
            new RegistFrame().setVisible(true);
            this.dispose();
        }
    }
}
