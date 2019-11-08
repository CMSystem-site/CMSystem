package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.CourseList;
import cn.lightina.managebooks.pojo.ProcessResult;
import cn.lightina.managebooks.pojo.User;
import cn.lightina.managebooks.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    //全部课程列表
    @GetMapping(value = "/allCourses")
    public String allCourses(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        //获取全部课程
        List<CourseList> list = courseService.getlist_all();
        model.addAttribute("list",list);
        return "showCourses";
    }

    //课程查找
    @PostMapping(value = "/findCourse")
    public String findCourse(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        /**
         *  合并查询
         */

        /*
        String course_str =request.getParameter("course_str");
        List<CourseList> list = courseService.findcourseByName(course_str);

        //如果为int型
        if(isInt(course_str)){
            Integer courseid = Integer.valueOf(course_str).intValue();
            List<CourseList> list2 = courseService.findcourseByID(courseid);
            list.addAll(list2);
        }

        model.addAttribute("list",list);
        */

        /**
         * 分离查询
         */
        List<CourseList> list_id = null;
        List<CourseList> list_name = null;
        String coursename =request.getParameter("coursename");
        String courseid_str = request.getParameter("courseid");


        if(coursename.length()!=0){
            list_name = courseService.findcourseByName(coursename);
        }

        if(isInt(courseid_str)){
            Integer courseid = Integer.valueOf(courseid_str).intValue();
            list_id = courseService.findcourseByID(courseid);
        }

        if(list_id == null){
            //未填写课程id：直接返回名称搜索列表
            model.addAttribute("list",list_name);
        }else if(list_name == null) {
            //未填写课程名称：直接返回id搜索列表
            model.addAttribute("list",list_id);
        }else{
                list_id.addAll(list_name);
                model.addAttribute("list",list_id);
        }

        return "showCourses";
    }

    //个人课程
    @GetMapping(value = "/myCourses")
    public String myCourses(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);

        List<CourseList> list = null;
        Integer userid = user.getUserID();

        list = courseService.findcourseByUserid(userid);

        model.addAttribute("list",list);
        return "myCourses";
    }


    //创建课程
    @GetMapping(value = "/addCourse")
    public String addCourse(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        String coursename= request.getParameter("coursename");
        Integer teacherID = user.getUserID();
        String teacherName = user.getUserName();

        CourseList courselist = new CourseList(coursename,teacherID,teacherName);
        Integer flag = courseService.addCourse(courselist);

        if(flag!=1){
            model.addAttribute("msg","创建课程失败！");
        }else{
            model.addAttribute("msg","创建成功！");
        }

        List<CourseList> list = null;
        Integer userid = user.getUserID();
        list = courseService.findcourseByUserid(userid);
        model.addAttribute("list",list);

        return "myCourses";
    }






    boolean isInt(String str){
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
