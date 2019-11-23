package cn.lightina.managebooks.pojo;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class ReComment {
    private Integer reCommentID;
    private Integer commentID;
    private Integer userID;
    private String text;
    private String date;
    private String email;
    private String website;
    private String userName;
    private String userType;

    public ReComment(Integer reCommentID, Long commentID, Integer userID,String text,Timestamp date,String userName,String userType){
        this.reCommentID = reCommentID;
        this.commentID = commentID.intValue();
        this.userID = userID;
        this.text = text;
        this.email = email;
        this.website = website;
        this.userName = userName;
        this.userType = userType;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(date);
    }

    public ReComment(Integer commentID, Integer userID,String text,String userName,String userType){
        this.commentID = commentID.intValue();
        this.userID = userID;
        this.text = text;
        this.email = email;
        this.website = website;
        this.userName = userName;
        this.userType = userType;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(new Date());
    }
}

