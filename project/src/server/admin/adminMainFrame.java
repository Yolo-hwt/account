package server.admin;

import global.MainFrame;
import global.operation;
import server.admin.adminFunc.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminMainFrame extends JFrame implements ActionListener {

    private JPanel contentPanel;//主面板
    private JMenuBar menuBar;//菜单栏

    private JMenu UserManage_menu;//子菜单
    private JMenu FileManage_menu;
    private JMenu view_message;
    private JMenu Other_menu;

    private JMenuItem view_user;//子菜单项
    private JMenuItem send_message;
    private JMenuItem loss_user;
    private JMenuItem del_user;
    private JMenuItem freeze_user;
    private JMenuItem apply_hasDown;//已办理列表
    private JMenuItem exit_admin;

    private ImageIcon picture;//图标
    private JLabel picture_label;
    //private User client.user;

    public adminMainFrame(){
        //this.client.user=client.user;
        this.setContentPane(adminMainFrame());
        this.setTitle("管理员业务");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(230,160,890,560);
        view_user.addActionListener(this);
        send_message.addActionListener(this);
        freeze_user.addActionListener(this);
        loss_user.addActionListener(this);
        del_user.addActionListener(this);
        apply_hasDown.addActionListener(this);
        exit_admin.addActionListener(this);
    }

    public JPanel adminMainFrame(){
        //主面板
        contentPanel=new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        //菜单栏
        menuBar=new JMenuBar();
        menuBar.setBounds(0,0,890,30);
        contentPanel.add(menuBar);
        //子菜单
        UserManage_menu=new JMenu("用户管理");
        UserManage_menu.setFont(new Font("黑体", Font.PLAIN, 18));
        FileManage_menu=new JMenu("请求处理");
        FileManage_menu.setFont(new Font("黑体", Font.PLAIN, 18));
        view_message =new JMenu("信息查询");
        view_message.setFont(new Font("黑体", Font.PLAIN, 18));
        Other_menu=new JMenu("其他功能");
        Other_menu.setFont(new Font("黑体", Font.PLAIN, 18));
        menuBar.add(UserManage_menu);
        menuBar.add(FileManage_menu);
        menuBar.add(view_message);
        menuBar.add(Other_menu);
        //用户管理菜单项
        view_user =new JMenuItem("用户列表");
        view_user.setFont(new Font("黑体", Font.PLAIN, 18));
        send_message =new JMenuItem("发送信息");
        send_message.setFont(new Font("黑体", Font.PLAIN, 18));
        UserManage_menu.add(view_user);
        UserManage_menu.add(send_message);
        //账户管理菜单项
        loss_user =new JMenuItem("处理账户挂失");
        loss_user.setFont(new Font("黑体", Font.PLAIN, 18));
        del_user =new JMenuItem("处理账户销户");
        del_user.setFont(new Font("黑体", Font.PLAIN, 18));
        freeze_user =new JMenuItem("处理账户冻结");
        freeze_user.setFont(new Font("黑体", Font.PLAIN, 18));
        FileManage_menu.add(loss_user);
        FileManage_menu.add(del_user);
        FileManage_menu.add(freeze_user);
        //信息查询菜单项
        apply_hasDown =new JMenuItem("已办列表");
        apply_hasDown.setFont(new Font("黑体", Font.PLAIN, 18));
        view_message.add(apply_hasDown);
        //其他功能菜单项
        exit_admin =new JMenuItem("退出系统");
        exit_admin.setFont(new Font("黑体", Font.PLAIN, 18));
        Other_menu.add(exit_admin);
        //添加主页面图片
        picture=new ImageIcon("src/images/admins.jfif");
        picture_label=new JLabel(picture);
        picture_label.setBounds(50,50,780,450);
        contentPanel.add(picture_label);

        return contentPanel;
    }

    public static void main(String[] args) {
        new adminMainFrame().setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String str;
        JMenuItem temp=(JMenuItem) e.getSource();
        if(temp==view_user){//用户列表
            new viewAlluser().setVisible(true);

        }else if(temp==send_message){//发送信息到用户
            new sendMesToUser().setVisible(true);

        }else if(temp==loss_user){//处理挂失
            str=operation.ADMIN_OPERATION_ENUM.getLossBacklogs.getOperation();
            new dealWithBacklogs(str).setVisible(true);

        }else if(temp==freeze_user){//处理冻结
            str=operation.ADMIN_OPERATION_ENUM.getFrozenBacklogs.getOperation();
            new dealWithBacklogs(str).setVisible(true);

        }else if(temp==del_user) {//处理销户
            str = operation.ADMIN_OPERATION_ENUM.getCloseBacklogs.getOperation();
            new dealWithBacklogs(str).setVisible(true);

        }else if(temp== apply_hasDown){//已办列表
            new viewDownBacklogs().setVisible(true);

        }else if(temp== exit_admin){//退出
            new MainFrame().setVisible(true);
        }

        this.dispose();
    }
}
