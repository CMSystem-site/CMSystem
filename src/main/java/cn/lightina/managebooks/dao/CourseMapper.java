package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.CourseSelection;
import cn.lightina.managebooks.pojo.CourseList;
import cn.lightina.managebooks.pojo.CourseSelection;
import cn.lightina.managebooks.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface CourseMapper {
    //增加课程
    Integer addCourse(@Param("courselist") CourseList courselist);
    //删除课程
    Integer deleteCourse(@Param("courseID")Integer courseID);

    //获取全部课程
    List<CourseList> getlist_all();

    //通过courseID查找
    CourseList findcourseByID(@Param("courseID")Integer courseID);

    //通过courseName查找
    List<CourseList> findcourseByName(@Param("courseName")String courseName);

    //通过userID查找
    List<CourseList> findcourseByUserid(@Param("userID")Integer userID);

    //查找teacherID
    Integer findteacherID(@Param("courseID")Integer courseID);

    //判断user是否选择某特定课程
    CourseSelection checkChosen(@Param("userID")Integer userID, @Param("courseID")Integer courseID);

    //选课
    Integer addCourseSelection(@Param("courseSelection")CourseSelection courseSelection);
    //退课
    Integer deleteCourseSelection(@Param("courseID")Integer courseID,@Param("studentID")Integer studentID);

    //选课名单
    List<CourseSelection> getSelectList(@Param("courseID")Integer courseID);
    //随机点名名单
    List<CourseSelection> getSelectListRandomly(@Param("courseID")Integer courseID,@Param("cnt")Integer cnt);

    //缺勤
    Integer setAbsence(@Param("courseID")Integer courseID,@Param("studentID")Integer studentID);

    //撤销缺勤
    Integer undoAbsence(@Param("courseID")Integer courseID,@Param("studentID")Integer studentID);
}
