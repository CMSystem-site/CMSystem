package cn.lightina.managebooks.service;

import cn.lightina.managebooks.pojo.Comment;
import cn.lightina.managebooks.pojo.ReComment;
import cn.lightina.managebooks.pojo.Topic;
import cn.lightina.managebooks.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumService {

    //添加讨论主题
    Integer addTopic(Topic topic);
    Integer checkTopic(Integer topicID);
    //获取某课程下的全部讨论主题
    List<Topic> getTopicByCourseID(Integer CourseID,String checkStatus);
    List<Topic> getTopicByTitle(Integer courseID,String title);
    List<Topic> getTopicByCheckStatus(String checkStatus);


    //获取单个讨论主题的内容
    Topic getTopicByTopicID(Integer TopicID);

    //在某个主题下面添加评论
    Integer addComment(Comment comment);

    //获取某个主题下面的所有评论
    List<Comment> getCommentByTopicID(Integer TopicID);

    //在某个评论下添加回复
    Integer addReComment(ReComment recomment);

    //获取某个评论下的所有回复
    List<ReComment> getReCommentByCommentID(Integer CommentID);

    User getUserByUserID(Integer userID);

    Integer updateTopic(Integer TopicID,Integer CommentCount);

    //删除话题
    Integer deleteTopic(Integer topicID);
    //删除评论
    Integer deleteComment(Integer commentID);
}
