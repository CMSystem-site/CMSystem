package cn.lightina.managebooks.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Topic {
    private Integer topicID;
    private Integer userID;

    private String date;
    private String tag;
    private Integer commentCount;
    private String title;
    private String abstracts;
    private String text;
    private Integer courseID;
    private String userName;
    private String userType;

    public Topic(Integer topicID, Integer userID, Timestamp date, String tag, Integer commentCount, String title, String abstracts, String text, Integer courseID,String userName,String userType){
        this.topicID = topicID;
        this.userID = userID;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(date);

        this.tag = tag;
        this.commentCount = commentCount;
        this.title = title;
        this.abstracts = abstracts;
        this.text = text;
        this.courseID = courseID;
        this.userName = userName;
        this.userType = userType;
    }

    public Topic(Integer userID,String tag, String title, String abstracts, String text, Integer courseID,String userName,String userType){
        this.topicID = topicID;
        this.userID = userID;
        this.tag = tag;
        this.title = title;
        this.abstracts = abstracts;
        this.text = text;
        this.courseID = courseID;
        this.userName = userName;
        this.userType = userType;

        this.commentCount = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(new Date());
    }
    public int getCommentCount(){
        return commentCount;
    }

}
