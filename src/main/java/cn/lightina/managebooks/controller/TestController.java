package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.CourseList;
import cn.lightina.managebooks.pojo.User;
import cn.lightina.managebooks.service.CourseService;
import cn.lightina.managebooks.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.ibatis.jdbc.Null;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.spi.FileSystemProvider;
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
    public String login(Model model, HttpServletRequest request){
        String userName = "";
        String password = "";
        User user = new User(userName,password);
        model.addAttribute("user", user);
        return "login_new";
    }

    //登陆验证
    @GetMapping(value = "/login_check")
    public String login_check(Model model, HttpServletRequest request){
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(userName,password);

        // 先添加user信息是为了在报错之后表单是上一次填写的内容
        model.addAttribute("user", user);

        // 判断用户名和密码
        if(userName == null) {
            model.addAttribute("msg","账号不能为空！");
            return "login_new";
        }
        else if(userName == null){
            model.addAttribute("msg","密码不能为空！");
            return "login_new";
        }

        User u = userService.checkUser(user);
        if(u==null){
            model.addAttribute("msg","账号/密码错误！");
            return "login_new";
        }

        // 成功之后更新model中的user信息
        model.addAttribute("user",u);
        return "index";
    }

    @GetMapping(value = "/register")
    public String register(Model model,HttpServletRequest request){
        String userName = "";
        String password = "";
        String phone = "";
        String usertype = "";
        User user = new User(userName,password,phone,usertype);
        model.addAttribute("user", user);

        return "register_new";
    }

    //用户注册
    @GetMapping(value = "/register_check")
    public String register_check(Model model,HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String usertype = request.getParameter("usertype");

        User user = new User(userName,password,phone,usertype);
        model.addAttribute("user", user);

        // 后端对表单内容进行校验,若检验未通过，返回
        if(userName.equals("")){
            model.addAttribute("msg","用户名不能为空！");
            return "register_new";
        }
        else if(password.equals("")){
            model.addAttribute("msg","密码不能为空！");
            return "register_new";
        }
        else if(phone.equals("")){
            model.addAttribute("msg","电话号码不能为空！");
            return "register_new";
        }
        else if(usertype.equals("null")){
            model.addAttribute("msg","用户类型不能为空");
            return "register_new";
        }


        Integer flag = userService.addUser(user);
        System.out.println(user.toString());


        if(flag!=1){
            model.addAttribute("msg","此用户名已被注册！");
            return "register_new";
        }else{
            model.addAttribute("msg","注册成功！");
            return "login_new";
        }


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
