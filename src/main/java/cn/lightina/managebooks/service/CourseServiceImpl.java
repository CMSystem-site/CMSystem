package cn.lightina.managebooks.service;

import cn.lightina.managebooks.dao.BookMapper;
import cn.lightina.managebooks.dao.CourseMapper;
import cn.lightina.managebooks.pojo.CourseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired(required = false)
    CourseMapper courseMapper;

    @Override
    public List<CourseList> getlist(Integer userID) {
        return courseMapper.getlist(userID);
    }

    @Override
    public List<CourseList> getlist_all() {
        return courseMapper.getlist_all();
    }
}
