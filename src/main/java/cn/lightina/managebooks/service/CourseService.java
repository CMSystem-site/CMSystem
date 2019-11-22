package cn.lightina.managebooks.service;

import cn.lightina.managebooks.pojo.*;
import cn.lightina.managebooks.pojo.CourseSelection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.List;

public interface CourseService {
    //创建课程
    Integer addCourse(CourseList courselist);

    //删除课程
    Integer deleteCourse(Integer courseID);

    //获取全部课程
    List<CourseList> getlist_all();

    //通过courseID查找课程
    CourseList findcourseByID(Integer courseID);

    //通过courseName查找
    List<CourseList> findcourseByName(String courseName);

    //通过userID查找
    List<CourseList> findcourseByUserid(Integer userID);

    //查找teacherID
    Integer findteacherID(Integer courseID);

    //通过userID判断是否选择某指定课程
    CourseSelection checkChosen(Integer userID, Integer courseID);

    //选课
    Integer addCourseSelection(CourseSelection courseSelection);

    //退课
    Integer deleteCourseSelection(Integer courseID,Integer studentID);

    //选课名单
    List<CourseSelection> getSelectList(Integer courseID);
    //随机点名
    List<CourseSelection> getSelectListRandomly(Integer courseID);
    //缺勤
    Integer setAbsence(Integer courseID,Integer studentID);

    //撤销缺勤
    Integer undoAbsence(Integer courseID,Integer studentID);

    //全体缺勤+1
    Integer setAbsenceAll(Integer courseID);

    //插入考勤码
    Integer setAutoCode(Integer courseID,Integer code);
    //删除考勤码
    Integer deleteAutoCode(Integer courseID);


    //获取考勤码记录
    Attendance checkAutoCode(Integer code);
    Attendance getAttendance(Integer courseID);

    //考勤状态置0
    Integer resetSignStatus(Integer courseID);
    //获取考勤状态
    Integer getSignStatus(Integer courseID,Integer studentID);
    //考勤状态置1
    Integer setSignStatus(Integer courseID,Integer studentID);

    //查询教师课程数
    Integer getTeaCourseNum(Integer userID);
    //查询学生课程数
    Integer getStuCourseNum(Integer userID);
    //查询课程总数
    Integer getAllCourseNum();
}
