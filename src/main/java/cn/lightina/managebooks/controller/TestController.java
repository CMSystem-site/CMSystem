package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.User;
import cn.lightina.managebooks.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {
    @Autowired
    UserService userService;

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
        if(u==null) return "login_new";
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
        String rname = request.getParameter("rname");
        String phone = request.getParameter("phone");
        String usertype = request.getParameter("usertype");

        User user = new User(userName,password,rname,phone,usertype);
        Integer flag = userService.addUser(user);

        if(flag!=1){
            model.addAttribute("msg","此用户名已被注册！");
        }else{
            model.addAttribute("msg","注册成功！");
        }

        return "register_new";
    }


    @GetMapping(value = "/resetPsd")
    public String resetPsd(){
        return "resetPsd_new";
    }


    //通过手机号找回密码
    @GetMapping(value = "/resetPsd_check")
    public String resetPsd_check(Model model, HttpServletRequest request){
        model.addAttribute("msg","未查找到用户信息");
        String phone = request.getParameter("phone");

        String pwd = userService.findPwd(phone);
        if(pwd!=null)
            model.addAttribute("msg","查找成功!您的密码为："+pwd);

        return "resetPsd_new";
    }


}
