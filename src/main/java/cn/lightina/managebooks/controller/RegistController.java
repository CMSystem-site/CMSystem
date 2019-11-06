package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.User;
import cn.lightina.managebooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/managebooks")
public class RegistController {

    @Autowired
    UserService userService;
//
//    @GetMapping(value = "/regist")
//    public String regist() {
//        return "regist";
//    }

    @GetMapping(value = "/detail2")
    public String detail2(Model model,
                        HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String rname = request.getParameter("rname");
        String phone = request.getParameter("phone");
        User user;
        if(userName == null) return "regist";
        else{
            user = new User(userName,password,rname,phone);
            Integer flag = userService.addUser(user);
            if(flag!=1) return "regist";
            return "login";
        }

    }


}
