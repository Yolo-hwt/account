package global.mes;

import java.io.Serializable;

public class message  implements Serializable {
    private static final long serialVersionUID=1L;
private accountUser user;
private accountFlag flag;
private String describe;

public message(String des){
    this.describe=des;
}
public message(accountUser user,accountFlag flag){
    this.user=user;
    this.flag=flag;
}
public message(accountUser user,accountFlag flag,String des){
    this.user=user;
    this.flag=flag;
    this.describe=des;
}

    public accountUser getUser() {
        return user;
    }

    public void setUser(accountUser user) {
        this.user = user;
    }

    public accountFlag getFlag() {
        return flag;
    }

    public void setFlag(accountFlag flag) {
        this.flag = flag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "message{" +
                "client.user=" + user +
                ", flag=" + flag +
                ", describe='" + describe + '\'' +
                '}';
    }
}
