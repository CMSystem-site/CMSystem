package cn.lightina.managebooks.service;

import cn.lightina.managebooks.pojo.Comment;
import cn.lightina.managebooks.pojo.ReComment;
import cn.lightina.managebooks.pojo.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumService {

    //添加讨论主题
    Integer addTopic(Topic topic);

    //获取全部讨论主题
    List<Topic> getTopic_all();

    //获取单个讨论主题的内容
    Topic getTopicByID(@Param("topicID") Integer TopicID);

    //在某个主题下面添加评论
    Integer addComment(@Param("comment") Comment comment);

    //获取某个主题下面的所有评论
    List<Comment> getComment_allByTopicID(@Param("topicID") Integer TopicID);

    //在某个评论下添加回复
    Integer addReComment(@Param("reComment") ReComment recomment);

    //获取某个评论下的所有回复
    List<ReComment> getReComment_allByCommentID(@Param("commentID") Integer CommentID);
}
