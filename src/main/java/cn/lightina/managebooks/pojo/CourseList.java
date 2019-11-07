package cn.lightina.managebooks.pojo;

import lombok.Data;

@Data
public class CourseList {
    private int courseID;
    private int teacherID;
    private String courseName;
    private String teacherName;

    public CourseList(int courseID, int teacherID, String courseName,String teacherName) {
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.courseName = courseName;
        this.teacherName = teacherName;
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

    @Override
    public String toString() {
        return "CourseList{" +
                "courseID=" + courseID +
                ", teacherID=" + teacherID +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
