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
    public Integer addCourse(CourseList courselist) {
        return courseMapper.addCourse(courselist);
    }

    @Override
    public List<CourseList> getlist_all() {
        return courseMapper.getlist_all();
    }

    @Override
    public List<CourseList> findcourseByID(Integer courseID) {
        return courseMapper.findcourseByID(courseID);
    }

    public List<CourseList> findcourseByName(String courseName){
        return courseMapper.findcourseByName(courseName);
    }

    @Override
    public List<CourseList> findcourseByUserid(Integer userID) {
        return courseMapper.findcourseByUserid(userID);
    }
}
