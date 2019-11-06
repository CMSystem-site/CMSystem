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
