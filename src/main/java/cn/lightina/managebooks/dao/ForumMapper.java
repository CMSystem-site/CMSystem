package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.Comment;
import cn.lightina.managebooks.pojo.ReComment;
import cn.lightina.managebooks.pojo.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumMapper {

    Integer addTopic(@Param("topic") Topic topic);
    List<Topic> getTopic_all();
    Topic getTopicByID(@Param("topicID") Integer TopicID);

    Integer addComment(@Param("comment") Comment comment);
    List<Comment> getComment_allByTopicID(@Param("topicID") Integer TopicID);

    Integer addReComment(@Param("reComment") ReComment recomment);
    List<ReComment> getReComment_allByCommentID(@Param("commentID") Integer CommentID);

}
