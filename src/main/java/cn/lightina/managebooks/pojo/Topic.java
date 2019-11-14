package cn.lightina.managebooks.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Topic {
//    private Integer id;
//    private String desction;
//    private Date createtime;
//    private Date modiftime;
//    private Integer author;
//    private int commentcount;
//    private int viewcount;
//    private int likecount; //文章点赞数
//    private String tag;
//    private String title;
//    private String username;
//    private Integer likeauthor;//点赞人
//    //文章和当前Cookie中的用户是多对一
//    private SaveSession saveSession;
    private Integer id;
    private String text;

    public Topic(String text) {
        this.text = text;
    }
}
