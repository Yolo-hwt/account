package server.admin.adminFunc;


import client.client_side;
import global.mes.accountFlag;
import global.mes.accountUser;
import global.mes.backLog;
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

public class dealWithBacklogs extends JFrame implements ActionListener{
        private JPanel ContentPanel;
        private JLabel Main_label;
        private JTable User_Table;
        private DefaultTableModel tableModel;
        private JScrollPane User_ScrollPane;
        private JButton Confirm_Button;
        private JButton Cancel_Button;
        private ImageIcon picture;

        private String operaName;
    public dealWithBacklogs(String oper){
            this.operaName =oper;
            this.setTitle("Processing user requests");
            this.setBounds(450,220,600,400);
            this.setVisible(true);
            this.setContentPane(this.backlogs());
            Confirm_Button.addActionListener(this);
            Cancel_Button.addActionListener(this);
        }
        public JPanel backlogs(){
            //
            ContentPanel=new JPanel();
            ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(ContentPanel);
            //
            picture=new ImageIcon("src/images/list.png");
            JLabel p = new JLabel(picture);
            p.setBounds(140,20,70,70);
            Main_label=new JLabel(operaName);
            Main_label.setBounds(250,40,300,50);
            Main_label.setFont(new Font("黑体", Font.PLAIN,45));
            //
            User_ScrollPane=new JScrollPane();
            User_ScrollPane.setBounds(50,100,480,160);
            //backlog信息表格填充
            setTableModel();
            User_Table=new JTable();
            User_Table.setModel(tableModel);
            User_ScrollPane.setViewportView(User_Table);
            //
            Confirm_Button = new JButton("处理");
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

    /**
     * //获取并设置用户信息表格
     */
    private void setTableModel(){
            String[] title={"用户电话","请求操作","请求时间","describe"};
            String[][] rowData = new String[20][4];

            String sta="false";
            accountFlag flag=new accountFlag(this.operaName,sta);
            message message=new message(null,flag,"apply to get backlogs");
            client.client_side.Myclient.sendData(message);
            message= client.client_side.Myclient.getData();

            if(message.getFlag().getStatusFlag().equals("success")){
                Vector<backLog> e= client_side.Myclient.getBackLogs();
                backLog log;
                for(int row=0;row<e.size();row++){
                    log=e.elementAt(row);
                    rowData[row][0]=log.getPhoneNum();
                    rowData[row][1]=log.getOperaTion();
                    rowData[row][2]=String.valueOf(log.getReportTime());
                    rowData[row][3]=log.getUserDesc();
                }
            }
            this.tableModel = new DefaultTableModel(rowData, title){
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            };
        }

    /**
     * //确认处理按钮响应
     */
    private void confirmToDeal() {
            int selectedrow = User_Table.getSelectedRow();
            // 未选择文件的情况
            if (selectedrow == -1) {
                JOptionPane.showMessageDialog(null, "未选择backlog！");
                return;
            }else {
                // 获取信息
                String phoneNum = (String) User_Table.getValueAt(selectedrow, 0);
                String useropera = (String) User_Table.getValueAt(selectedrow, 1);

                String opera = operation.userToAdmin(useropera);

                accountFlag flag = new accountFlag(opera, "false");
                accountUser user = new accountUser();
                user.setPhoneNum(phoneNum);
                message mes = new message(user, flag);
                mes.setDescribe("Admin_Processing user requests");
                client_side.Myclient.sendData(mes);
                message result = client_side.Myclient.getData();

                if (result.getFlag().getStatusFlag().equals("success")) {
                    JOptionPane.showMessageDialog(null, "处理成功！");
                    new dealWithBacklogs(this.operaName).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "操作失败！");
                    return;
                }
            }
        }
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Confirm_Button) {
                this.confirmToDeal();
            }
            if (e.getSource() == Cancel_Button) {
                this.dispose();
                new adminMainFrame().setVisible(true);
            }
        }
}
