package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.CourseList;
import cn.lightina.managebooks.pojo.User;
import cn.lightina.managebooks.service.CourseService;
import cn.lightina.managebooks.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;

    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/table")
    public String table() {
        return "table";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login_new";
    }

    //登陆验证
    @GetMapping(value = "/login_check")
    public String login_check(Model model, HttpServletRequest request){
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user;
        if(userName == null) return "login_new";

        user = new User(userName,password);
        User u = userService.checkUser(user);
        if(u==null){
            model.addAttribute("msg","账号/密码错误！");
            return "login_new";
        }
        model.addAttribute("user", u);
        request.getSession().setAttribute("user", u);
        return "index";
    }

    @GetMapping(value = "/register")
    public String register(){
        return "register_new";
    }

    //用户注册
    @GetMapping(value = "/register_check")
    public String regist_check(Model model,HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String usertype = request.getParameter("usertype");

        User user = new User(userName,password,phone,usertype);
        Integer flag = userService.addUser(user);
        model.addAttribute("user", user);

        if(flag!=1){
            model.addAttribute("msg","此用户名已被注册！");
        }else{
            model.addAttribute("msg","注册成功！");
        }

        return "register_new";
    }

    //修改密码
    @GetMapping(value = "/resetPsd")
    public String resetPsd(Model model,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("username", user.getUserName());
        return "resetPsd";
    }

    @GetMapping(value = "/resetPsd_check")
    public String resetPsd_check(Model model, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        String userName = (String) request.getSession().getAttribute("username");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        String pwd3 = request.getParameter("pwd3");

        if(user.getUserPwd().equals(pwd1)){

            if(pwd2.equals(pwd3)){
                //两次新密码输入一致
                user.setUserPwd(pwd2);

                userService.updatePwd(user);
                model.addAttribute("user",user);
                model.addAttribute("msg","密码修改成功!");
                request.getSession().setAttribute("user",user);
                return "userinfo";
            }else{
                model.addAttribute("msg","两次新密码输入不一致！");
                return "resetPsd";
            }
        }else{
            model.addAttribute("msg","旧密码输入错误!");
            return "resetPsd";
        }
    }

    @GetMapping(value = "/findPsd")
    public String findPsd(){
        return "findPsd";
    }
    //通过手机号找回密码
    @GetMapping(value = "/findPsd_check")
    public String findPsd_check(Model model, HttpServletRequest request){
        model.addAttribute("msg","未查找到用户信息");
        String phone = request.getParameter("phone");

        String pwd = userService.findPwd(phone);
        if(pwd!=null)
            model.addAttribute("msg","查找成功!您的密码为："+pwd);

        return "resetPsd_new";
    }

    //个人信息
    @GetMapping(value = "/userinfo")
    public String userinfo(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        request.getSession().setAttribute("user", user);
        return "userinfo";
    }


}
