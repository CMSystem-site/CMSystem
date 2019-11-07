package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.CourseList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    //增加课程
    Integer addCourse(@Param("course") CourseList course);
    //删除课程
    Integer deleteCourse(@Param("course")CourseList course);

    //获取全部课程
    List<CourseList> getlist_all();

    //获取指定用户的课程
    List<CourseList> getlist(@Param("userID")Integer userID);


}
