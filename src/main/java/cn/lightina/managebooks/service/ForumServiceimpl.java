package cn.lightina.managebooks.service;

import cn.lightina.managebooks.dao.ForumMapper;
import cn.lightina.managebooks.pojo.Comment;
import cn.lightina.managebooks.pojo.ReComment;
import cn.lightina.managebooks.pojo.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumServiceimpl implements ForumService{
    @Autowired(required = false)
    ForumMapper forumMapper;

    //添加讨论主题
    @Override
    public Integer addTopic(Topic topic){
        return forumMapper.addTopic(topic);
    }

    //获取全部讨论主题
    @Override
    public List<Topic> getTopicByCourseID(Integer courseID){
        return forumMapper.getTopicByCourseID(courseID);
    }


    //获取单个讨论主题的内容
    @Override
    public Topic getTopicByTopicID(Integer topicID){
        return forumMapper.getTopicByTopicID(topicID);
    }

    //在某个主题下面添加评论
    @Override
    public Integer addComment(Comment comment){
        return forumMapper.addComment(comment);
    }

    //获取某个主题下面的所有评论
    @Override
    public List<Comment> getComment_allByTopicID(Integer topicID){
        return forumMapper.getComment_allByTopicID(topicID);
    }

    //在某个评论下添加回复
    @Override
    public Integer addReComment(ReComment recomment){
        return forumMapper.addReComment(recomment);
    }

    //获取某个评论下的所有回复
    @Override
    public List<ReComment> getReComment_allByCommentID(Integer commentID){
        return forumMapper.getReComment_allByCommentID(commentID);
    }
}
