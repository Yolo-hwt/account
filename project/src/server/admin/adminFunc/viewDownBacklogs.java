package server.admin.adminFunc;

import client.client_side;
import global.mes.accountFlag;
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

public class viewDownBacklogs extends JFrame implements ActionListener{
        private JPanel ContentPanel;
        private JLabel Main_label;
        private JTable logs_Table;
        private DefaultTableModel tableModel;
        private JScrollPane Logs_ScrollPane;
        private JButton Confirm_Button;
        private ImageIcon picture;

        public viewDownBacklogs(){
            this.setTitle("finishBacklog list");
            this.setBounds(450,220,600,400);
            this.setVisible(true);
            this.setContentPane(this.finishLogs());
            Confirm_Button.addActionListener(this);
        }
        public JPanel finishLogs(){
            //
            ContentPanel=new JPanel();
            ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(ContentPanel);
            //
            picture=new ImageIcon("src/images/list.png");
            JLabel p = new JLabel(picture);
            p.setBounds(140,20,70,70);
            Main_label=new JLabel("finishBacklog list");
            Main_label.setBounds(250,40,300,50);
            Main_label.setFont(new Font("黑体", Font.PLAIN,45));
            //
            Logs_ScrollPane =new JScrollPane();
            Logs_ScrollPane.setBounds(50,100,480,160);
            //backlog信息表格填充
            setTableModel();
            logs_Table =new JTable();
            logs_Table.setModel(tableModel);
            Logs_ScrollPane.setViewportView(logs_Table);
            //
            Confirm_Button = new JButton("确认");
            Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
            Confirm_Button.setBounds(140, 290, 100, 27);
            //
            ContentPanel.setLayout(null);
            ContentPanel.add(Main_label);
            ContentPanel.add(p);
            ContentPanel.add(Logs_ScrollPane);
            ContentPanel.add(Confirm_Button);
            return ContentPanel;
        }

        /**
         * //获取并设置用户信息表格
         */
        private void setTableModel(){
            String[] title={"用户电话","请求操作","请求时间","处理时间","describe"};
            String[][] rowData = new String[20][5];

            String sta="false";
            accountFlag flag=new accountFlag(operation.ADMIN_OPERATION_ENUM.getFinfshBackLogs.getOperation(), sta);
            message message=new message(null,flag,"apply to get finishBacklogs");
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
                    rowData[row][3]=String.valueOf(log.getFinishTime());
                    rowData[row][4]=log.getUserDesc();
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
        }
    }

