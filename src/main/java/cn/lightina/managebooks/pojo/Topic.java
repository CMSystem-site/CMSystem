package cn.lightina.managebooks.pojo;

import lombok.Data;

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

    public Topic(Integer topicID,Integer userID,String tag, String title, String abstracts, String text){
        this.topicID = topicID;
        this.userID = userID;
        this.tag = tag;
        this.title = title;
        this.abstracts = abstracts;
        this.text = text;

        this.commentCount = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(new Date());
    }

}
