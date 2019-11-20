package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.User;
import cn.lightina.managebooks.service.CourseService;
import cn.lightina.managebooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;

    @GetMapping(value = "/index_stu")
    public String index(){

        return "index_stu";
    }

    @GetMapping(value = "/index")
    public String index1(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        if(user.getUserType().equals("教师"))
            return "index_tea";

        return "index_stu";
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
        return "login";
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
            return "login";
        }
        else if(userName == null){
            model.addAttribute("msg","密码不能为空！");
            return "login";
        }

        User u = userService.checkUser(user);
        if(u==null){
            model.addAttribute("msg","账号/密码错误！");
            return "login";
        }

        // 成功之后更新model中的user信息
        model.addAttribute("user",u);
        request.getSession().setAttribute("user", u);
        if(u.getUserType().equals("学生"))
            return "index_stu";
        else if(u.getUserType().equals("教师"))
            return "index_tea";
        else if(u.getUserType().equals("管理员"))
            return "index_admin";
        else{
            model.addAttribute("msg","用户身份非法");
            return "login";
        }

    }

    @GetMapping(value = "/register")
    public String register(Model model,HttpServletRequest request){
        String userName = "";
        String password = "";
        String phone = "";
        String usertype = "";
        User user = new User(userName,password,phone,usertype);
        model.addAttribute("user", user);

        return "register";
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
            return "register";
        }
        else if(password.equals("")){
            model.addAttribute("msg","密码不能为空！");
            return "register";
        }
        else if(phone.equals("")){
            model.addAttribute("msg","电话号码不能为空！");
            return "register";
        }
        else if(usertype.equals("null")){
            model.addAttribute("msg","用户类型不能为空");
            return "register";
        }

        User u = userService.checkPhone(phone);
        if(u==null){
            Integer flag = userService.addUser(user);
            if(flag!=1){
                model.addAttribute("msg","注册失败，请刷新重试！");
                return "register";
            }else{
                model.addAttribute("msg","注册成功！");
                return "login";
            }
        }else{
            model.addAttribute("msg","此手机号码已被注册！");
            return "register";
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

        return "findPsd";
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
