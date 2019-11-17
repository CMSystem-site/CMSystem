package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.Comment;
import cn.lightina.managebooks.pojo.ReComment;
import cn.lightina.managebooks.pojo.Topic;
import cn.lightina.managebooks.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumMapper {

    Integer addTopic(@Param("topic") Topic topic);
    List<Topic> getTopicByCourseID(@Param("courseID") Integer CourseID);
    List<Topic> getTopicByTitle(@Param("courseID")Integer CourseID,@Param("title")String title);
    Topic getTopicByTopicID(@Param("topicID") Integer TopicID);


    Integer addComment(@Param("comment") Comment comment);
    List<Comment> getCommentByTopicID(@Param("topicID") Integer TopicID);

    Integer addReComment(@Param("reComment") ReComment recomment);
    List<ReComment> getReCommentByCommentID(@Param("commentID") Integer CommentID);

    User getUserByUserID(@Param("userID") Integer userID);
    Integer updateTopic(@Param("topicID")Integer TopicID,@Param("CommentCount")Integer CommentCount);

    Integer deleteTopic(@Param("topicID")Integer topicID);
    Integer deleteComment(@Param("commentID")Integer commentID);
}
