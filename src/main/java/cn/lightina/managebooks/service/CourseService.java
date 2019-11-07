package cn.lightina.managebooks.service;

import cn.lightina.managebooks.pojo.CourseList;
import org.springframework.stereotype.Controller;

import java.util.List;

public interface CourseService {
    // 获取某用户的课程
    List<CourseList> getlist(Integer userID);

    //获取全部课程
    List<CourseList> getlist_all();

    //通过courseID查找课程
    List<CourseList> findcourseByID(Integer courseID);

    //通过courseName查找
    List<CourseList> findcourseByName(String courseName);

}
