package server.server_side;/*
@author 杨马也
@creat $(DATE)-$(TIME)
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class serverFrame extends JFrame{

    private JPanel ContentPanel;
    private JLabel Main_label;
    private JTextField Port_Txt;
    private JLabel title_label;
    private JTextField title_Txt;
    private JLabel Port_label;
    private JLabel IP_label;
    private JTextArea message_area;
    private JScrollPane message_ScrollPane;
    private JButton confirm_button;
    private JButton cancel_button;
    private ImageIcon picture;
    private JLabel pictureLabel;

    private serverConnection serverConnection;

    public static void main(String[] args) {
        new serverFrame().setVisible(true);
    }

    public serverFrame(){
        this.setTitle("服务器端");
        this.setBounds(450, 60, 700, 700);
        this.setVisible(true);
        this.setContentPane(Server());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirm_button.setEnabled(true);
        cancel_button.setEnabled(false);
        confirm_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchPerformed(e);
            }
        });
        cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitPerformed(e);
            }
        });
    }
    //GUI界面构造
    public JPanel Server() {
        ContentPanel = new JPanel();
        ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(ContentPanel);
        //
        picture = new ImageIcon("src/images/server.png");
        pictureLabel = new JLabel(picture);
        pictureLabel.setBounds(50, 10, 200, 200);
        Main_label = new JLabel("开源银行服务器");
        Main_label.setBounds(275, 50, 400, 100);
        Main_label.setFont(new Font("黑体", Font.PLAIN, 50));
        //
        IP_label = new JLabel("服务器当前状态：");
        IP_label.setBounds(80, 220, 220, 40);
        IP_label.setFont(new Font("黑体", Font.PLAIN, 25));
        Port_label = new JLabel("已设置端口号码：");
        Port_label.setBounds(80, 270, 220, 40);
        Port_label.setFont(new Font("黑体", Font.PLAIN, 25));
        title_label = new JLabel("IP 地 址 / 客户操作");
        title_label.setBounds(190, 320, 380, 40);
        title_label.setFont(new Font("黑体", Font.PLAIN, 30));
        //
        title_Txt = new JTextField("   当前服务器未启动");
        title_Txt.setBounds(280, 226, 250, 35);
        title_Txt.setFont(new Font("黑体", Font.PLAIN, 20));
        title_Txt.setForeground(Color.RED);
        title_Txt.setEditable(false);
        Port_Txt = new JTextField();
        Port_Txt.setBounds(280, 270, 250, 35);
        Port_Txt.setFont(new Font("黑体", Font.PLAIN, 20));
        Port_Txt.setEditable(false);
        //
        message_area = new JTextArea();
        message_area.setEditable(false);
        message_ScrollPane = new JScrollPane();
        message_ScrollPane.setBounds(60, 370, 560, 190);
        message_ScrollPane.setViewportView(message_area);
        //
        confirm_button = new JButton("启动");
        confirm_button.setFont(new Font("黑体", Font.PLAIN, 20));
        confirm_button.setBounds(140, 580, 80, 35);
        cancel_button = new JButton("断开");
        cancel_button.setFont(new Font("黑体", Font.PLAIN, 20));
        cancel_button.setBounds(440, 580, 80, 35);
        //
        ContentPanel.setLayout(null);
        ContentPanel.add(Main_label);
        ContentPanel.add(pictureLabel);
        ContentPanel.add(title_label);
        ContentPanel.add(Port_label);
        ContentPanel.add(IP_label);
        ContentPanel.add(title_Txt);
        ContentPanel.add(Port_Txt);
        ContentPanel.add(message_ScrollPane);
        ContentPanel.add(cancel_button);
        ContentPanel.add(confirm_button);
        return ContentPanel;
    }

    /**
     * 事件响应
     * @param evt
     */
    //启动服务器
    public void launchPerformed(ActionEvent evt){
        new Thread(() -> {
                JOptionPane.showMessageDialog(null, "启动成功！");
                title_Txt.setText("   服务器已启动 等待连接中");
                Port_Txt.setText("        31010");
                try {
                    //创建连接实例
                    serverConnection=new serverConnection(serverFrame.this);
                    serverConnection.start();

                    confirm_button.setEnabled(false);
                    cancel_button.setEnabled(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
        }).start();
    }

    /**
     * 断开服务器
     * @param e
     */
    public void exitPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null, "成功断开！");
        message_area.setText("");
        title_Txt.setText("   当前服务器未启动");
        Port_Txt.setText("");
        confirm_button.setEnabled(true);
        cancel_button.setEnabled(false);
        serverConnection.user_process.setExit_user(false);//用以断开连接 false
    }

    /**
     * 服务器显示区
     * @param messageToDisplay
     */
    public void disMessageinArea(final String messageToDisplay){
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run() // updates displayArea
                    {
                        message_area.append( messageToDisplay+"\n" ); // append message
                    } // end method run
                } // end anonymous inner class
        ); // end call to SwingUtilities.invokeLater
    }

    public JTextField getTitle_Txt() {
        return title_Txt;
    }

    public void setTitle_Txt(JTextField title_Txt) {
        this.title_Txt = title_Txt;
    }

    public void setIP_label(JLabel IP_label) {
        this.IP_label = IP_label;
    }
}