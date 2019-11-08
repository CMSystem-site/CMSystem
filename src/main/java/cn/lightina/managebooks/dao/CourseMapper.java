package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.CourseList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    //增加课程
    Integer addCourse(@Param("courselist") CourseList courselist);
    //删除课程
    Integer deleteCourse(@Param("course")CourseList course);

    //获取全部课程
    List<CourseList> getlist_all();

    //通过courseID查找
    List<CourseList> findcourseByID(@Param("courseID")Integer courseID);

    //通过courseName查找
    List<CourseList> findcourseByName(@Param("courseName")String courseName);

    //通过userID查找
    List<CourseList> findcourseByUserid(@Param("userID")Integer userID);

}
