package global.mes;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户类
 */
public class accountUser implements Serializable {

    private String cardNumber;//卡号
    private String passWords;//密码
    private String userName;//用户名
    private String phoneNum;//手机号
    private Timestamp loginTime;//注册时间

    public accountUser(){
        this.cardNumber="0";
        this.passWords="0";
        this.userName="0";
        this.phoneNum="0";
        this.loginTime=new Timestamp(0);

    }
    public accountUser(String cardN,String passW){
        this.cardNumber=cardN;
        this.passWords=passW;
        this.userName="0";
        this.phoneNum="0";
        this.loginTime=new Timestamp(0);

    }
    public accountUser(String cardN,String passW,String userN,String pNum,Timestamp nowTime){
        this.cardNumber=cardN;
        this.passWords=passW;
        this.userName=userN;
        this.phoneNum=pNum;
        this.loginTime=nowTime;

    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassWords() {
        return passWords;
    }

    public void setPassWords(String passWords) {
        this.passWords = passWords;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "accountUser{" +
                "cardNumber='" + cardNumber + '\'' +
                ", passWords='" + passWords + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
