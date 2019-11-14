package cn.lightina.managebooks.pojo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Integer id;
    private Integer topicID;
    private String comment;
    private Integer commentator;
    private Date commentTime;
    private Integer commentCount;
    private Integer read;//评论是否已读  1为未读，0为已读

    private SaveSession user;

    private Topic topic;//回复所在的文章


    //该条评论下有哪些评论
    //一对多
    private List<Recomment> reComments;

    //该评论有多少回复
    private Integer reCommentCount;
}
