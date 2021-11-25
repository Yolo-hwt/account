package global.mes;

import java.io.Serializable;

public class accountFlag implements Serializable {
    /**
     * operaTion
     * TODO:标识业务名称
     * statusFlag
     * TODO:标识请求操作的成功与否
     */
    private String operaTion;
    private String statusFlag;
    private Double amount;
    public accountFlag(String operaTion,String statusFlag){
        this.operaTion=operaTion;
        this.statusFlag=statusFlag;
        this.amount=0.0;
    }
    public String getOperaTion() {
        return operaTion;
    }

    public void setOperaTion(String operaTion) {
        this.operaTion = operaTion;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "accountFlag{" +
                "operaTion='" + operaTion + '\'' +
                ", statusFlag='" + statusFlag + '\'' +
                ", amount=" + amount +
                '}';
    }
}
