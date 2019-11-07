package cn.lightina.managebooks.pojo;

import lombok.Data;

@Data
public class Course {
    private int courseID;
    private int teacherID;
    private int studentID;
    private String courseName;
    private int absence;

    public Course(int courseID, int teacherID, int studentID, String courseName) {
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.studentID = studentID;
        this.courseName = courseName;
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

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }
}
