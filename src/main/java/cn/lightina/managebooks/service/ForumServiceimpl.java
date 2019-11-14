package cn.lightina.managebooks.service;

import cn.lightina.managebooks.dao.ForumMapper;
import cn.lightina.managebooks.pojo.Topic;
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
    public List<Topic> getTopic_all(){
        return forumMapper.getTopic_all();
    }
}
