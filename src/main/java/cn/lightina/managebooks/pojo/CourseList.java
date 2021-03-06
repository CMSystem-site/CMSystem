package cn.lightina.managebooks.pojo;

import lombok.Data;

@Data
public class CourseList {
    private int courseID;
    private String courseName;
    private int teacherID;
    private String teacherName;
    private int isChosen;
    private int code;

    public CourseList(String courseName, int teacherID, String teacherName, int code) {
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.code = code;
    }

    public CourseList(String courseName, int teacherID, String teacherName) {
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
    }

    public CourseList(int courseID, String courseName, int teacherID, String teacherName) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
    }

    public CourseList(int courseID, String courseName, int teacherID, String teacherName, Integer code) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.code = code;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getIsChosen() {
        return isChosen;
    }

    public void setIsChosen(int isChosen) {
        this.isChosen = isChosen;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CourseList{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", teacherID=" + teacherID +
                ", teacherName='" + teacherName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
