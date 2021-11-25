package server.admin.adminFunc;

import client.client_side;
import com.mysql.cj.protocol.Message;
import global.mes.accountFlag;
import global.mes.accountUser;
import global.mes.message;
import global.operation;
import server.admin.adminMainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class viewAlluser extends JFrame implements ActionListener{
        private JPanel ContentPanel;
        private JLabel Main_label;
        private JTable User_Table;
        private DefaultTableModel tableModel;
        private JScrollPane User_ScrollPane;
        private JButton Confirm_Button;
        private JButton Cancel_Button;
        private ImageIcon picture;
        private JLabel p;

        public viewAlluser(){
            this.setTitle("用户列表");
            this.setBounds(450,220,600,400);
            this.setVisible(true);
            this.setContentPane(this.UserList());
            Confirm_Button.addActionListener(this);
            Cancel_Button.addActionListener(this);
        }
        public JPanel UserList(){
            //
            ContentPanel=new JPanel();
            ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(ContentPanel);
            //
            picture=new ImageIcon("src/images/list.png");
            p=new JLabel(picture);
            p.setBounds(140,20,70,70);
            Main_label=new JLabel("用户列表");
            Main_label.setBounds(250,40,300,50);
            Main_label.setFont(new Font("黑体", Font.PLAIN,45));
            //
            User_ScrollPane=new JScrollPane();
            User_ScrollPane.setBounds(50,100,480,160);
            //用户信息表格填充
            setTableModel();
            User_Table=new JTable();
            User_Table.setModel(tableModel);
            User_ScrollPane.setViewportView(User_Table);
            //
            Confirm_Button = new JButton("确认");
            Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
            Confirm_Button.setBounds(140, 290, 100, 27);
            Cancel_Button = new JButton("取消");
            Cancel_Button.setFont(new Font("黑体", Font.PLAIN, 20));
            Cancel_Button.setBounds(340, 290, 100, 27);
            //
            ContentPanel.setLayout(null);
            ContentPanel.add(Main_label);
            ContentPanel.add(p);
            ContentPanel.add(User_ScrollPane);
            ContentPanel.add(Confirm_Button);
            ContentPanel.add(Cancel_Button);
            return ContentPanel;
        }
        //获取并设置用户信息表格
        private void setTableModel(){
            String[] title={"用户名","用户电话","用户卡号","注册时间"};
            String[][] rowData = new String[20][4];

            String opera= operation.ADMIN_OPERATION_ENUM.getAllUser.getOperation();
            String sta="false";
            accountFlag flag=new accountFlag(opera,sta);
            message message=new message(null,flag,"apply to get users");
            client.client_side.Myclient.sendData(message);
            message= client.client_side.Myclient.getData();

            if(message.getFlag().getStatusFlag().equals("success")){
                Vector<accountUser> e= client_side.Myclient.getAllUser();
                accountUser user;
                for(int row=0;row<e.size();row++){
                    user=e.elementAt(row);
                    rowData[row][0]=user.getUserName();
                    rowData[row][1]=user.getPhoneNum();
                    rowData[row][2]=user.getCardNumber();
                    rowData[row][3]=String.valueOf(user.getLoginTime());
                }
            }
            this.tableModel = new DefaultTableModel(rowData, title){
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            };
        }
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Confirm_Button) {
                this.dispose();
                new adminMainFrame().setVisible(true);
            }
            if (e.getSource() == Cancel_Button) {
                this.dispose();
                new adminMainFrame().setVisible(true);
            }
        }
    }
