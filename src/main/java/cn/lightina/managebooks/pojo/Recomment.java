package cn.lightina.managebooks.pojo;
import lombok.Data;

import java.util.Date;
@Data
public class Recomment {
    private Integer id;
    private Integer recommentor;
    private String reComment;
    private Date reCommentTime;
    private Integer commentID; //该回复对应的评论
    private Integer commentorID;//该回复的被回复者
    private Integer topicID;//该回复的被回复者
    private Integer read;//被回复者是否读取了该回复？

    private SaveSession user; //获取回复者的相关信息

    private Topic topic;
}
