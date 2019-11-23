package cn.lightina.managebooks.pojo;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Integer commentID;
    private Integer userID;
    private Integer topicID;
    private String date;
    private String text;
    private Integer reCommentCount;
    private String email;
    private String website;
    private String userName;
    private String userType;

    //该条评论下有哪些评论
    //一对多
    private List<ReComment> reComments;

    public Comment(Long commentID, Integer userID, Integer topicID, Timestamp date, String text, Integer reCommentCount,
                   String userName, String userType){
        this.commentID = commentID.intValue();
        this.userID = userID;
        this.topicID = topicID;
        this.text = text;
        this.reCommentCount = reCommentCount;
        this.email = email;
        this.website = website;
        this.userName = userName;
        this.userType = userType;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(date);
    }
    public Comment(Integer userID, Integer topicID,String text, String userName,String userType){
        this.userID = userID;
        this.topicID = topicID;
        this.text = text;
        this.userName = userName;
        this.userType = userType;

        this.reCommentCount = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(new Date());

    }

    public Integer getCommentID() {
        return this.commentID;
    }
}
