package cn.lightina.managebooks.pojo;
import lombok.Data;

import java.util.Date;
@Data
public class ReComment {
    private Integer reCommentID;
    private Integer commentID;
    private Integer userID;
    private Date reCommentTime;
    private String text;
    private String email;
    private String website;
}
