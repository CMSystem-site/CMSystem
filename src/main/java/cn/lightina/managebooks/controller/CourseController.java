package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.CourseList;
import cn.lightina.managebooks.pojo.User;
import cn.lightina.managebooks.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    //全部课程
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

        String course_str =request.getParameter("course_str");
        List<CourseList> list = courseService.findcourseByName(course_str);

        //如果为int型
        if(isInt(course_str)){
            Integer courseid = Integer.valueOf(course_str).intValue();
            List<CourseList> list2 = courseService.findcourseByID(courseid);
            list.addAll(list2);
        }

        model.addAttribute("list",list);

        for (CourseList c:list)
            System.out.println(c.toString());

        return "showCourses";
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
