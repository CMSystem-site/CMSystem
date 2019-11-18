package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.CourseList;
import cn.lightina.managebooks.pojo.CourseSelection;
import cn.lightina.managebooks.pojo.ProcessResult;
import cn.lightina.managebooks.pojo.User;
import cn.lightina.managebooks.service.CourseService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

        List<CourseList> list = getCourselist(user);

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
        CourseList list_id = null;
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
                list_name.add(list_id);
                model.addAttribute("list",list_name);
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

        if(coursename.equals("")){
            model.addAttribute("msg","创建课程失败！");
        }else{
            CourseList courselist = new CourseList(coursename,teacherID,teacherName);
            Integer flag = courseService.addCourse(courselist);
            if(flag!=1){
                model.addAttribute("msg","创建课程失败！");
            }else{
                model.addAttribute("msg","创建成功！");
            }
        }





        List<CourseList> list = null;
        Integer userid = user.getUserID();
        list = courseService.findcourseByUserid(userid);
        model.addAttribute("list",list);

        return "myCourses";
    }

    //某课程信息
    @RequestMapping("/courseinfo/{courseID}")
    public String courseinfo(@PathVariable(value = "courseID")Integer courseID,
                             Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        CourseList courselist = courseService.findcourseByID(courseID);
        CourseSelection cs = courseService.checkChosen(user.getUserID(),courseID);
        if(cs==null){
            courselist.setIsChosen(0);
        }else {
            courselist.setIsChosen(1);
        }
        model.addAttribute("courselist",courselist);

        return "courseinfo";
    }

    //选课
    @RequestMapping("/chooseCourse/{courseID}")
    public String chooseCourse(@PathVariable(value = "courseID")Integer courseID,
                               Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        CourseList course = courseService.findcourseByID(courseID);
        CourseSelection cs = new CourseSelection(courseID,course.getCourseName(),course.getTeacherID(),user.getUserID());


        int flag = courseService.addCourseSelection(cs);
        if(flag==1){
            model.addAttribute("msg","选课成功！");
        }else{
            model.addAttribute("msg","选课失败，请刷新重试！");
        }

        List<CourseList> list = courseService.findcourseByUserid(user.getUserID());
        model.addAttribute("list",list);

        return "myCourses";
    }

    //退课
    @RequestMapping("/dropCourse/{courseID}")
    public String dropCourse(@PathVariable(value = "courseID")Integer courseID,
                               Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        int flag = courseService.deleteCourseSelection(courseID,user.getUserID());

        if(flag==1){
            model.addAttribute("msg","退课成功！");
        }else{
            model.addAttribute("msg","退课失败，请刷新重试！");
        }

        List<CourseList> list = courseService.findcourseByUserid(user.getUserID());
        model.addAttribute("list",list);


        return "myCourses";
    }


    //教师删除课程
    @RequestMapping("/deleteCourse/{courseID}")
    public String deleteCourse(@PathVariable(value = "courseID")Integer courseID,
                               Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        if(courseService.findteacherID(courseID)!=user.getUserID()){
            model.addAttribute("这不是您的课程！");
            List<CourseList> list = courseService.findcourseByUserid(user.getUserID());
            model.addAttribute("list",list);
            return "myCourses";
        }
        int flag = courseService.deleteCourse(courseID);
        if(flag == 1){
            model.addAttribute("msg","删除课程成功！");
        }else{
            model.addAttribute("msg","退课失败，请刷新重试！");
        }

        List<CourseList> list = courseService.findcourseByUserid(user.getUserID());
        model.addAttribute("list",list);
        return "myCourses";

    }


    //选课学生名单
    @RequestMapping("/studentlist/{courseID}")
    public String studentlist(@PathVariable(value = "courseID")Integer courseID,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user",user);
        request.setAttribute("courseID",courseID);

        //选课学生名单
        List<CourseSelection> selectlist = courseService.getSelectList(courseID);
        model.addAttribute("selectlist",selectlist);

        return "studentlist";
    }

    //手动考勤
    @RequestMapping("/absence/{courseID}/{studentID}")
    public String absence(@PathVariable(value = "courseID")Integer courseID,@PathVariable(value = "studentID")Integer studentID,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("courseID",courseID);

        //设置缺勤
        int flag = courseService.setAbsence(courseID,studentID);
        if(flag != 1){
            model.addAttribute("msg","操作失败，请重试！");
        }

        //选课学生名单
        List<CourseSelection> selectlist = courseService.getSelectList(courseID);
        model.addAttribute("selectlist",selectlist);

        return "studentlist";
    }

    //撤销缺勤
    @RequestMapping("/undo_absence/{courseID}/{studentID}")
    public String undo_absense(@PathVariable(value = "courseID")Integer courseID,@PathVariable(value = "studentID")Integer studentID,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("courseID",courseID);

        //撤销缺勤
        int flag = courseService.undoAbsence(courseID,studentID);
        if(flag != 1){
            model.addAttribute("msg","操作失败，请重试！");
        }

        //选课学生名单
        List<CourseSelection> selectlist = courseService.getSelectList(courseID);
        model.addAttribute("selectlist",selectlist);

        return "studentlist";
    }

    //随机考勤
    @RequestMapping("/random_absence/{courseID}/{cnt}")
    public String random_absence(@PathVariable(value = "courseID")Integer courseID,
                                 @PathVariable(value = "cnt")Integer cnt,
                                 Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user",user);
        request.setAttribute("courseID",courseID);

        //选课学生名单
        List<CourseSelection> selectlist = courseService.getSelectListRandomly(courseID,cnt);



        model.addAttribute("selectlist",selectlist);
        return "studentlist";
    }

    //自动考勤P1 (+1)
    @RequestMapping("/auto_absence/{courseID}")
    public String auto_absence(@PathVariable(value = "courseID")Integer courseID,@PathVariable(value = "code")String code,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        model.addAttribute("courseID",courseID);
        return "auto_absence";
    }




    boolean isInt(String str){
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //更新isChosen属性
    List<CourseList> getCourselist(User user){
        CourseSelection course = null;    // 选课记录实体类
        //获取全部课程
        List<CourseList> list1 = courseService.getlist_all();
        List<CourseList> list = new ArrayList<CourseList>();

        for(CourseList c : list1){
            course = courseService.checkChosen(user.getUserID(),c.getCourseID());
            if(course==null){
                c.setIsChosen(0);
            }else{
                c.setIsChosen(1);
            }
            list.add(c);
        }
        return list;
    }

}
