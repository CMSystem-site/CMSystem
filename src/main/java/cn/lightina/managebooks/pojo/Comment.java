package cn.lightina.managebooks.pojo;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

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

    //该条评论下有哪些评论
    //一对多
    private List<ReComment> reComments;

    public Comment(Integer userID, Integer topicID,String text, String email,String website){
        this.userID = userID;
        this.topicID = topicID;
        this.text = text;
        this.email = email;
        this.website = website;

        this.reCommentCount = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(new Date());

    }

}
