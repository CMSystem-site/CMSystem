package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumMapper {

    Integer addTopic(@Param("topic") Topic topic);
    List<Topic> getTopic_all();

}
