package client.mainGUI;/*
@author 杨马也
@creat $(DATE)-$(TIME)
*/

import global.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageFrame extends JFrame implements ActionListener {
    private JPanel  contentPanel;
    private JLabel Main_Label;
    private ImageIcon picture;
    private JLabel picture_Label;
    private JTextPane MessageText;
    private JScrollPane MessageScrollPane;
    private JButton Confirm_Button;
    private JButton Cancel_Button;

    public MessageFrame(){
        this.setTitle("银行概况");
        this.setContentPane(this.MessageFrame());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(350,200,750,550);
        Confirm_Button.addActionListener(this);
        Cancel_Button.addActionListener(this);
    }
    public JPanel MessageFrame(){
        //主面板
        contentPanel=new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        //主标签
        picture = new ImageIcon("src/images/message.png");
        picture_Label = new JLabel(picture);
        picture_Label.setBounds(125, 20, 75, 75);
        Main_Label = new JLabel("开源银行运行概况");
        Main_Label.setBounds(220, 12, 500, 100);
        Main_Label.setFont(new Font("黑体", Font.PLAIN, 45));
        //文本域
        MessageText=new JTextPane();
        MessageScrollPane=new JScrollPane(MessageText);
        MessageScrollPane.setBounds(44,105,650,320);
        MessageText.setText("一、关于开源银行\n" +
                "\t开源银行是一家总部设在深圳的全国性股份制商业银行（SZ000001）。其前身深圳发展银行是中国内地首家公开上市的全国性股份制银行。中国平安及其控股子公司为本行控股股东。截至2021年6月，本集团在职员工共37,384 人，通过101家分行及1110家营业机构为客户提供多种金融服务。\n" +
                "\n" +
                "二、发展战略\n" +
                "\t本行以打造“中国最卓越、全球领先的智能化零售银行”为战略目标，坚持“科技引领、零售突破、对公做精”十二字策略方针，持续深化战略转型，全力打造“数字银行、生态银行、平台银行”三张名片，为客户提供有温度的金融服务。\n" +
                "\n" +
                "三、业绩概览\n" +
                "\t本行坚持不断深化数字化经营，各项业务保持稳健增长。2021年上半年，营业收入846.8亿元，同比增长8.1%。转型至今，近几年营收复合增长率13.2%，在对标行中增幅最高。对公与零售营收基本实现“四六”分；净利润175.83亿元，同比增长28.5%。同时资产质量持续优化，2021年6月末，本行不良贷款率1.08%，较上年末下降 0.1个百分点；关注贷款、逾期 60 天以上贷款及逾期90天以上贷款的占比分别为0.96%、0.91%和0.79%，较上年末分别下降0.15、0.17和0.09个百分点。拨备覆盖率、逾期60天以上贷款拨备覆盖率及逾期90天以上贷款拨备覆盖率分别为259.53%、306.11%和 355.67%。与转型初期相比，本行不良率、拨备覆盖率等指标优化至历史最好水平。\n" +
                "\n" +
                "四、零售业务：全面打造“五位一体”服务模式\n" +
                "\t本行零售业务持续贯彻“3+2+1”经营策略，即推动“基础零售、私行财富、消费金融”3 大业务模块升级，重点关注“风险控制、成本控制”2大核心能力提升，着力推动以AI Bank 为内核、以开放银行为外延的“1大生态”的构筑与经营，并升级以“综合化银行、AI银行、远程银行、线下银行和开放银行”相互衔接并有机融合的“五位一体”新模式，为零售业务二次腾飞注入新动能。\n" +
                "\t2021年上半年，本行零售各项业务实现较好增长，零售营业收入492.13亿元，同比增长10.9%，在全行营业收入中占比58.1%；零售净利润117.22 亿元，同比增长46.3%，在全行净利润中占比66.7%；零售贷款占比在对标行中最高，为61%。\n" +
                "\n" +
                "五、对公业务：全面打响五张牌\n" +
                "\n" +
                "\t本行对公业务坚持“行业化、专业化、特色化”发展之路，持续践行“3+2+1”经营策略，即聚焦“行业银行、交易银行、综合金融”3大业务支柱；重点发力“战略客群、小微客群”2 大核心客群；严守 资产质量“1条生命线”。与此同时，着力打造“供应链金融、票据一体化、客户经营平台、复杂投融及生态化综拓”五张牌。\n" +
                "\t2021年上半年，本行对公业务营业收入224.48亿元，同比增长 9.8%。对公存款价量双优，在压降近千亿主动负债的前提下，对公存款逆势增长1,441亿，日均存款增长超千亿；存款结构显著优化，对公日均活期占对公日均存款比例较年初提升3.3个百分点至34.2%，成本率优化20bp至1.97%；客户基础显著改善，客户数较年初增长近5万，增量为去年同期的3.5倍。\n" +
                "\n" +
                "六、资金同业业务：打造五张金色名片\n" +
                "\t本行资金同业业务坚持“服务金融市场、服务同业客户、服务实体经济”的理念，围绕“3+2+1”经营策略，即聚焦“新交易、新同业、新资管”3大业务方向，提升“销售能力、交易能力”2大核心能力，打造“1个智慧资金系统平台”。同时升级打造“行业顶尖的金融交易专家、行业一流的避险服务专家、金融产品机构销售的领军服务商、领先的数字生态托管银行及品类最全的开放式理财平台”五张金色名片。\n" +
                "\t2021年上半年，本行主要做市交易品种交易量的市场份额保持市场前列，黄金交易量和债券交易量的市场份额分别为9.2%和2.1%，在外汇交易中心公布的2021年二季度衍生品做市排名中，继续保持市场领先地位；“平安避险”业务不断升级，2021年上半年，“平安避险”外汇及利率衍生产品业务交易量147.91亿美元，同比增长77.5%；数字生态托管银行建设取得突破，逐步打响“托管就是平安”的市场口碑。2021年6月末，本行托管净值规模6.9万亿元，较上年末增长18.8%，增幅位居国内股份制商业银行第一位；本行的全资子公司平安理财管理规模快速增长，2021年6月末，平安理财总资产63.69亿元，净资产59.89亿元；非保本理财产品余额7,561亿元，较上年末增长16.6%，增速名列市场前茅。\n" +
                "\n" +
                "七、践行企业社会责任\n" +
                "\t在服务实体经济方面，本行认真落实今年四部委的要求，积极响应“减费让利”两项倡议，助力小微企业和个体工商户等市场主体减负，支持民营企业、中小微企业的高质量发展。2021年6月末，本行民营企业贷款余额较上年末增长12.8%，在企业贷款余额中占比72%；上半年，本行小微企业贷款发放额1,806.84亿元，同比增长 58.6%，新发放贷款加权平均利率较上年全年下降52个基点。\n" +
                "\t在支持乡村振兴方面，本行探索并创新乡村振兴服务的“421”模式，即通过“融资、融智、品牌和科技”四大赋能，推动“综合金融与三农场景”两者结合，致力于打造“集政府、农业龙头企业、银行、保险、农研院所”于一体的产业振兴共建平台。2021 年上半年，本行投放产业扶贫和乡村振兴资金51.47亿元，惠及6.9万农户；自金融扶贫和乡村振兴项目启动以来，累计投放金额达304.56亿元。\n" +
                "\t在践行绿色金融方面，本行坚持以绿色金融促进实体经济发展的经营导向，大力支持清洁能源、节能环保、清洁生产、生态环境、基础设施绿色升级等绿色产业的发展，打造绿色金融品牌。2021年6月末，本行及平安理财绿色金融业务余额602.18亿元，较上年末增长58.7%；本行绿色信贷余额368.59亿元，较上年末增长62.5%。\n" +
                "\n" +
                "八、企业荣誉\n" +
                "\t近年来，本行的业务发展和经营特色深受权威机构好评。继2020年荣获《欧洲货币》“全球最佳数字银行”大奖后，今年先后荣获《欧洲货币》“中国最佳家族办公室”、《亚洲货币》“2021年度中国最佳技术创新交易银行”“中国最佳移动服务交易银行”等十多项大奖。另外，本行自主研发的“空中柜台”，不断将传统柜面业务向线上渠道迁移，荣获2021年德国iF设计奖；本行绿色金融服务案例还荣获财联社颁发的“碳中和先锋奖”。\n" +
                "\t未来，平安银行将继续积极响应国家战略，不断增强服务实体经济的能力，持续加强风险管理防控，全面推进 AI Bank 体系建设，向着“中国最卓越、全球领先的智能化零售银行”的目标不断迈进。");
        MessageText.setEditable(false);
        //确认按钮
        Cancel_Button = new JButton("残忍离开");
        Cancel_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Cancel_Button.setBounds(110, 440, 150, 27);
        //取消按钮
        Confirm_Button = new JButton("立即开卡");
        Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
        Confirm_Button.setBounds(465, 440, 150, 27);
        //滚动条
        MessageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contentPanel.setLayout(null);
        contentPanel.add(Main_Label);
        contentPanel.add(picture_Label);
        //contentPanel.add(MessageText);
        contentPanel.add(MessageScrollPane);
        contentPanel.add(Confirm_Button);
        contentPanel.add(Cancel_Button);
        return contentPanel;
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Cancel_Button){
            JOptionPane.showMessageDialog(null, "成功取消！");
            this.dispose();
            new MainFrame().setVisible(true);
        }
        if(e.getSource()==Confirm_Button){
            this.dispose();
            new RegistFrame().setVisible(true);
        }
    }

}
