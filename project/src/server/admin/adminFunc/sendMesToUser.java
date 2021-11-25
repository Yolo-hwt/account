package server.admin.adminFunc;

import global.mes.message;
import server.admin.adminMainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sendMesToUser extends JFrame implements ActionListener{
        private JPanel contentPane;
        private JLabel Main_Label;
        private JLabel Mes_Label;
        private JTextField Mes_Txt;
        private JButton Confirm_Button;
        private JButton Cancel_Button;
        private ImageIcon picture;
        private JLabel picture_Label;
        private JLabel picture1;

        public sendMesToUser() {
            this.setTitle("sendMesToUser");
            this.setResizable(false);
            this.setContentPane(this.sendMesFrame());
            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setBounds(500, 250, 565, 400);
            Confirm_Button.addActionListener(this);
            Cancel_Button.addActionListener(this);
        }

        public JPanel sendMesFrame() {
            // 中间容器
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            //主标签
            String filename="src/images/sendmes.png" ;
            picture = new ImageIcon(filename);
            picture_Label = new JLabel(picture);
            picture_Label.setBounds(240, 3, 70, 70);
            Main_Label = new JLabel("下发用户信息");
            Main_Label.setBounds(170, 80, 200, 50);
            Main_Label.setFont(new Font("黑体", Font.PLAIN, 30));
            // 信息标签
            picture = new ImageIcon("src/images/meslable.png");
            picture1 = new JLabel(picture);
            picture1.setBounds(60, 140, 30, 20);

            //信息文本框
            Mes_Label = new JLabel("信 息：");
            Mes_Label.setBounds(93, 140, 70, 22);
            Mes_Label.setFont(new Font("黑体", Font.PLAIN, 20));

            Mes_Txt = new JTextField();
            Mes_Txt.setBounds(170, 150, 220, 130);

            // 确认按钮
            Confirm_Button = new JButton("确认");
            Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
            Confirm_Button.setBounds(140, 300, 85, 27);
            // 取消按钮
            Cancel_Button = new JButton("取消");
            Cancel_Button.setFont(new Font("黑体", Font.PLAIN, 20));
            Cancel_Button.setBounds(313, 300, 85, 27);
            
            //设置布局 添加部件
            contentPane.setLayout(null);
            contentPane.add(Main_Label);
            contentPane.add(picture_Label);
            contentPane.add(picture1);
            contentPane.add(Mes_Label);
            contentPane.add(Mes_Txt);
            contentPane.add(Confirm_Button);
            contentPane.add(Cancel_Button);
            return contentPane;
        }

    public static void main(String[] args) {
        new sendMesToUser().setVisible(true);
    }
        public JTextField getMes_Txt() {
            return Mes_Txt;
        }
        public void Clear(){
            this.getMes_Txt().setText("");
        }
        public void actionPerformed(ActionEvent e) {
            JButton temp = (JButton) e.getSource();
            if (temp == Cancel_Button) {
                new adminMainFrame().setVisible(true);
                this.dispose();
            }
            if (temp == Confirm_Button) {
                message mestoUser=new message(null,null,this.Mes_Txt.getText());
                //
            }
        }
}
