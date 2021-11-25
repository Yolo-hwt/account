package client.user;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.frameProcess.applyProcess;
import global.MainFrame;
import global.mes.*;
import global.operation;

public class userMainFrame extends JFrame implements ActionListener{
    private JPanel contentPanel;//主面板
    private JMenuBar menuBar;//菜单栏
    private ImageIcon picture;//图标
    private JLabel picture_label;
    /***账户管理菜单***
     * 申请挂失账户
     * 申请冻结账户
     * 申请销户账户
     */
    JMenu accountManagement;
    JMenuItem applyLoss;
    JMenuItem applyFrozen;
    JMenuItem applyClose;
    /***账户存取款业务菜单***
     * 存款
     * 取款
     */
    JMenu depositAndWithdrawMoney;
    JMenuItem depositMoney;
    JMenuItem withdrawMoney;
    /***个人信息管理菜单***
     * 查看个人信息
     * 更改个人密码
     * 退出登录
     */
    JMenu selfInfo;
    JMenuItem watchSelfinfo;
    JMenuItem changePassword;
    JMenuItem exitLogin;

    private accountUser user;//获取登录用户信息

    public void setUserMainFrame(userMainFrame frame){
        frame.setTitle("用户业务");
        frame.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(230,160,890,560);
    }
    public userMainFrame(accountUser user) throws HeadlessException {
        this.user = user;
        //界面参数
        setUserMainFrame(this);
        setContentPane(this.userMainFrame());
        //按钮监听
        applyLoss.addActionListener(this);
        applyFrozen.addActionListener(this);
        applyClose.addActionListener(this);
        depositMoney.addActionListener(this);
        withdrawMoney.addActionListener(this);
        watchSelfinfo.addActionListener(this);
        changePassword.addActionListener(this);
        exitLogin.addActionListener(this);
    }
    public JPanel userMainFrame() {
//主面板
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
//菜单栏
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 890, 30);
        contentPanel.add(menuBar);
//子菜单
        accountManagement = new JMenu("个人账户业务");
        selfInfo = new JMenu("个人信息业务");
        depositAndWithdrawMoney = new JMenu("存取款业务");

        accountManagement.setFont(new Font("黑体", Font.PLAIN, 18));
        selfInfo.setFont(new Font("黑体", Font.PLAIN, 18));
        depositAndWithdrawMoney.setFont(new Font("黑体", Font.PLAIN, 18));
        menuBar.add(accountManagement);
        menuBar.add(depositAndWithdrawMoney);
        menuBar.add(selfInfo);
//账户管理菜单
        applyLoss = new JMenuItem("申请挂失");
        applyFrozen = new JMenuItem("申请冻结账户");
        applyClose = new JMenuItem("申请销户");

        applyLoss.setFont(new Font("黑体", Font.PLAIN, 18));
        applyFrozen.setFont(new Font("黑体", Font.PLAIN, 18));
        applyClose.setFont(new Font("黑体", Font.PLAIN, 18));
        accountManagement.add(applyLoss);
        accountManagement.add(applyFrozen);
        accountManagement.add(applyClose);
//账户存取款业务菜单
        depositMoney = new JMenuItem("存款");
        withdrawMoney = new JMenuItem("取款");

        depositMoney.setFont(new Font("黑体", Font.PLAIN, 18));
        withdrawMoney.setFont(new Font("黑体", Font.PLAIN, 18));
        depositAndWithdrawMoney.add(depositMoney);
        depositAndWithdrawMoney.add(withdrawMoney);
//个人信息管理菜单
        watchSelfinfo=new JMenuItem("个人信息");
        changePassword = new JMenuItem("更改密码");
        exitLogin = new JMenuItem("退出登录");
        watchSelfinfo.setFont(new Font("黑体", Font.PLAIN, 18));
        changePassword.setFont(new Font("黑体", Font.PLAIN, 18));
        exitLogin.setFont(new Font("黑体", Font.PLAIN, 18));
        selfInfo.add(changePassword);
        selfInfo.add(exitLogin);
        selfInfo.add(watchSelfinfo);

        //添加主页面图片
        picture=new ImageIcon("src/images/userFrame.png");
        picture_label=new JLabel(picture);
        picture_label.setBounds(10,20,850,500);
        contentPanel.add(picture_label);

        return contentPanel;
    }
    public void actionPerformed(ActionEvent e) {
        JMenuItem temp=(JMenuItem) e.getSource();
        if(temp==applyLoss){//申请挂失
            accountFlag flag=new accountFlag(operation.USER_OPERATION_ENUM.lossCard.getOperation(),"false");
            message mes=new message(user,flag);
            new applyOperation(mes).setVisible(true);

        }else if(temp==applyFrozen){//申请冻结
            accountFlag flag=new accountFlag(operation.USER_OPERATION_ENUM.frozenAccount.getOperation(),"false");
            message mes=new message(user,flag);
            new applyOperation(mes).setVisible(true);

        }else if(temp==applyClose){//申请销户
            accountFlag flag=new accountFlag(operation.USER_OPERATION_ENUM.closeAccount.getOperation(),"false");
            message mes=new message(user,flag);
            new applyOperation(mes).setVisible(true);

        }else if(temp==depositMoney){//存款
            accountFlag flag=new accountFlag(operation.USER_OPERATION_ENUM.depositMoney.getOperation(),"false");
            message mes=new message(user,flag);
            new depositOrWithdraw(mes).setVisible(true);

        }else if(temp==withdrawMoney){//取款
            accountFlag flag=new accountFlag(operation.USER_OPERATION_ENUM.withdrawMoney.getOperation(),"false");
            message mes=new message(user,flag);
            new depositOrWithdraw(mes).setVisible(true);

        }else if(temp==watchSelfinfo){//查看个人信息
            new viewSelfMessage(user).setVisible(true);

        }else if(temp==changePassword){//更改个人密码
            new ChangePassword(user).setVisible(true);

        }else if(temp==exitLogin){//退出登录
            new MainFrame().setVisible(true);
        }
        this.dispose();//释放当前界面资源
    }

}
