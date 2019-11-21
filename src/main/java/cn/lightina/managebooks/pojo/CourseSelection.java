package cn.lightina.managebooks.pojo;

import lombok.Data;

@Data
public class CourseSelection {
    private int courseID;
    private String courseName;
    private int teacherID;
    private int studentID;
    private String studentName;
    private int absence;
    private int status;



    public CourseSelection(int courseID, String courseName, int teacherID, int studentID, int absence,int status, String studentName) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.studentID = studentID;
        this.absence = absence;
        this.status = status;
        this.studentName = studentName;
    }

    public CourseSelection(int courseID, String courseName, int teacherID, int studentID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.studentID = studentID;
    }

    public CourseSelection(int courseID, String courseName, int teacherID, int studentID, int absence,int status) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.studentID = studentID;
        this.absence = absence;
        this.status = status;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
