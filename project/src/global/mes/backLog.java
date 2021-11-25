package global.mes;

import java.io.Serializable;
import java.sql.Timestamp;

public class backLog implements Serializable {
    private static final long serialVersionUID=1L;
    private String phoneNum;
    private String operaTion;
    private Timestamp reportTime;
    private Timestamp finishTime;
    private int solve;
    private String userDesc;
    private int hasTold;

    public backLog(String phoneN,String oprera, Timestamp reT,Timestamp fiT,
            int solve,String dec,int told){
        this.phoneNum =phoneN;
        this.operaTion=oprera;
        this.reportTime=reT;
        this.finishTime=fiT;
        this.solve=solve;
        this.userDesc =dec;
        this.hasTold=told;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getOperaTion() {
        return operaTion;
    }

    public int getSolve() {
        return solve;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public Timestamp getReportTime() {
        return reportTime;
    }
}
