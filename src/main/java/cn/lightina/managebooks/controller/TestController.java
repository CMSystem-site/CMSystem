package cn.lightina.managebooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
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

    @GetMapping(value = "/register")
    public String register(){
        return "register_new";
    }

    @GetMapping(value = "/resetPsd")
    public String resetPsd(){
        return "resetPsd_new";
    }

}
