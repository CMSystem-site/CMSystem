package cn.lightina.managebooks.pojo;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String userName;
    private String passWd;
    private String rname;
    private String phone;
    private String usertype;

    public User(String userName, String passWd) {
        this.userName = userName;
        this.passWd = passWd;
    }

    public User(String userName, String passWd, String rname, String phone,String usertype) {
        this.userName = userName;
        this.passWd = passWd;
        this.rname = rname;
        this.phone = phone;
        this.usertype = usertype;
    }

    public User() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUserName() {
        return userName;

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWd='" + passWd + '\'' +
                ", rname='" + rname + '\'' +
                ", phone='" + phone + '\'' +
                ", usertype='" + usertype + '\'' +
                '}';
    }
}
