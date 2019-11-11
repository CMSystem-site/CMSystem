package cn.lightina.managebooks.service;

import cn.lightina.managebooks.dao.BookMapper;
import cn.lightina.managebooks.dao.CourseMapper;
import cn.lightina.managebooks.pojo.CourseSelection;
import cn.lightina.managebooks.pojo.CourseList;
import cn.lightina.managebooks.pojo.CourseSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
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
    public Integer deleteCourse(Integer courseID) {
        return courseMapper.deleteCourse(courseID);
    }

    @Override
    public List<CourseList> getlist_all() {
        return courseMapper.getlist_all();
    }

    @Override
    public CourseList findcourseByID(Integer courseID) {
        return courseMapper.findcourseByID(courseID);
    }

    public List<CourseList> findcourseByName(String courseName){
        return courseMapper.findcourseByName(courseName);
    }

    @Override
    public List<CourseList> findcourseByUserid(Integer userID) {
        return courseMapper.findcourseByUserid(userID);
    }

    @Override
    public Integer findteacherID(Integer courseID) {
        return courseMapper.findteacherID(courseID);
    }

    @Override
    public CourseSelection checkChosen(Integer userID, Integer courseID) {
        return courseMapper.checkChosen(userID,courseID);
    }

    @Override
    public Integer addCourseSelection(CourseSelection courseSelection) {
        return courseMapper.addCourseSelection(courseSelection);
    }

    @Override
    public Integer deleteCourseSelection(Integer courseID, Integer studentID) {
        return courseMapper.deleteCourseSelection(courseID,studentID);
    }


}
