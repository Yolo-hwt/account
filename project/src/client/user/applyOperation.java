package client.user;

import client.frameProcess.applyProcess;
import global.mes.accountFlag;
import global.mes.accountUser;
import global.mes.message;
import global.operation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class applyOperation extends JFrame implements ActionListener{
        private JPanel contentPane;
        private JLabel Main_Label;
        private JLabel OperaTion_Label;
        private JLabel ConfirmPassword_Label;
        private JTextField OperaTion_Txt;
        private JPasswordField ConfirmPassword_Txt;
        private JButton Confirm_Button;
        private JButton Cancel_Button;
        private ImageIcon picture;
        private JLabel picture_Label;
        private JLabel picture1;
        private JLabel picture2;

        private accountFlag myflag;
        private accountUser user;
        public applyOperation(message mes) {
            this.myflag=mes.getFlag();
            this.user=mes.getUser();
            this.setTitle("user_apply_operation");
            this.setResizable(false);
            this.setContentPane(this.applyFrame());
            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setBounds(500, 250, 565, 372);
            Confirm_Button.addActionListener(this);
            Cancel_Button.addActionListener(this);
        }
        public JPanel applyFrame() {
            ArrayList<String>str= set_Path_Opera(myflag.getOperaTion());
            String filename=str.get(0);
            String opera=str.get(1);
            // 中间容器
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);

            //主标签
            picture = new ImageIcon(filename);
            picture_Label = new JLabel(picture);
            picture_Label.setBounds(240, 3, 80, 80);
            Main_Label = new JLabel("个人账户管理申请");
            Main_Label.setBounds(110, 50, 500, 100);
            Main_Label.setFont(new Font("黑体", Font.PLAIN, 45));

            // 操作标签
            picture = new ImageIcon("src/images/operation.png");
            picture1 = new JLabel(picture);
            picture1.setBounds(100, 157, 30, 20);
            OperaTion_Label = new JLabel("操  作：");
            OperaTion_Label.setBounds(128, 149, 90, 40);
            OperaTion_Label.setFont(new Font("黑体", Font.PLAIN, 20));

            // 密码
            picture = new ImageIcon("src/images/y3.png");
            picture2 = new JLabel(picture);
            picture2.setBounds(100, 210, 30, 20);
            ConfirmPassword_Label = new JLabel("密  码：");
            ConfirmPassword_Label.setBounds(128, 202, 90, 40);
            ConfirmPassword_Label.setFont(new Font("黑体", Font.PLAIN, 20));

            // 文本域
            OperaTion_Txt = new JTextField();
            OperaTion_Txt.setBounds(222, 160, 176, 24);
            OperaTion_Txt.setColumns(10);
            OperaTion_Txt.setEnabled(false);
            OperaTion_Txt.setText(opera);
            OperaTion_Txt.setFont(new Font("黑体", Font.PLAIN, 20));
            //确认密码文本域
            ConfirmPassword_Txt = new JPasswordField();
            ConfirmPassword_Txt.setBounds(222, 210, 176, 24);

            // 确认按钮
            Confirm_Button = new JButton("确认");
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
            contentPane.add(OperaTion_Label);
            contentPane.add(picture1);
            contentPane.add(ConfirmPassword_Label);
            contentPane.add(picture2);
            contentPane.add(OperaTion_Txt);
            contentPane.add(ConfirmPassword_Txt);
            contentPane.add(Confirm_Button);
            contentPane.add(Cancel_Button);
            return contentPane;
        }
        private ArrayList<String> set_Path_Opera(String myopera){
            String picturePath="";
            String opera="";
            switch (operation.USER_OPERATION_ENUM.valueOf(myopera)){
                case lossCard:
                    picturePath="src/images/loss.png";
                    opera= operation.USER_OPERATION_ENUM.lossCard.getOperation();
                    break;
                case closeAccount:
                    picturePath="src/images/close.png";
                    opera= operation.USER_OPERATION_ENUM.closeAccount.getOperation();
                    break;
                case frozenAccount:
                    picturePath="src/images/frozen.png";
                    opera= operation.USER_OPERATION_ENUM.frozenAccount.getOperation();
                    break;
            }
            ArrayList<String>res= new ArrayList<>();
            res.add(picturePath);
            res.add(opera);
            return res;
        }
        public void Clear(){
        this.ConfirmPassword_Txt.setText("");
    }

        public String getConfirmPassword() {
            return String.valueOf(this.ConfirmPassword_Txt.getPassword());
        }

        public void actionPerformed(ActionEvent e) {
            JButton temp = (JButton) e.getSource();
            if (temp == Cancel_Button) {
                applyProcess.applyCancel(this,user);
            }
            if (temp == Confirm_Button) {
                applyProcess.applyConfirm(this,new message(user,myflag));
            }
        }
}
