package cn.lightina.managebooks.service;

import cn.lightina.managebooks.pojo.Topic;

import java.util.List;

public interface ForumService {

    //添加讨论主题
    Integer addTopic(Topic topic);

    //获取全部讨论主题
    List<Topic> getTopic_all();
}
