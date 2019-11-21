package cn.lightina.managebooks.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Attendance {
    Integer courseID;
    Integer code;//考勤码
    Timestamp time; //考勤码生效时间

    public Attendance(Integer courseID, Integer code) {
        this.courseID = courseID;
        this.code = code;
    }

    public Attendance(Integer courseID, Integer code, Timestamp time) {
        this.courseID = courseID;
        this.code = code;
        this.time = time;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "courseID=" + courseID +
                ", code=" + code +
                ", time=" + time +
                '}';
    }
}
