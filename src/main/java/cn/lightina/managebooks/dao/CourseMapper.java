package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.*;
import cn.lightina.managebooks.pojo.CourseSelection;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
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
    List<CourseSelection> getSelectListRandomly(@Param("courseID")Integer courseID);

    //缺勤
    Integer setAbsence(@Param("courseID")Integer courseID,@Param("studentID")Integer studentID);

    //撤销缺勤
    Integer undoAbsence(@Param("courseID")Integer courseID,@Param("studentID")Integer studentID);

    //全体缺勤+1
    Integer setAbsenceAll(@Param("courseID")Integer courseID);

    //插入考勤码
    Integer setAutoCode(@Param("courseID")Integer courseID,@Param("code")Integer code);
    //删除考勤码
    Integer deleteAutoCode(@Param("courseID")Integer courseID);

    //获取考勤码记录
    Attendance checkAutoCode(@Param("code")Integer code);
    Attendance getAttendance(@Param("courseID")Integer courseID);

    //签到状态置0
    Integer resetSignStatus(@Param("courseID")Integer courseID);
    //获取签到状态
    Integer getSignStatus(@Param("courseID")Integer courseID,@Param("studentID")Integer studentID);
    //签到状态置1
    Integer setSignStatus(@Param("courseID")Integer courseID,@Param("studentID")Integer studentID);

    //查询教师课程数量
    Integer getTeaCourseNum(@Param("userID")Integer userID);
    //查询学生课程数量
    Integer getStuCourseNum(@Param("userID")Integer userID);
    //查询课程总数
    Integer getAllCourseNum();
}
