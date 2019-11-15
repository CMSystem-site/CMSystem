package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.Topic;
import cn.lightina.managebooks.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ForumController {

    @Autowired
    ForumService forumService;

    @GetMapping("/forum")
    public String forum(){
        return "forum";
    }

    @GetMapping("/forum/single")
    public String forumSingle(Model model,HttpServletRequest request){
        List<Topic> topiclist = forumService.getTopic_all();
        model.addAttribute("topic", topiclist.get(0));
        return "forumSingle";
    }

    @GetMapping("/forum/page")
    public String forumPage(){
        return "forumaddtemp";
    }

    @RequestMapping("/forum/editMarkdown")
    public String editForumMarkdown(){
        return "forumEditMarkdown";
    }

    @GetMapping("/forum/addMarkdown")
    public String addForumMarkdown(Model model,HttpServletRequest request){

        String text = request.getParameter("text");
        System.out.println(text);
        Topic topic = new Topic(text);
        System.out.println(topic.toString());
        int flag = forumService.addTopic(topic);
        if(flag!=1){
            model.addAttribute("msg","创建话题失败！");
        }else{
            model.addAttribute("msg","创建话题成功！");
        }
        System.out.println(topic.toString());
        return "forumEditMarkdown";
    }

    @GetMapping("/forum/showMarkdown")
    public String showForumMarkdown(Model model,HttpServletRequest request){


        List<Topic> topiclist = forumService.getTopic_all();
        model.addAttribute("topic", topiclist.get(0));
        return "forumShowMarkdown";
    }


    // 点击课程进入该课程的讨论区
    @RequestMapping("/forum/{courseID}")
    public String forumIndex(@PathVariable(value = "courseID")Integer courseID,
                             Model model, HttpServletRequest request){
        return "showForumIndex";
    }

    // 点击某讨论区的帖子
    @RequestMapping("/forum/{courseID}/{topicID}")
    public String showForumTopic(@PathVariable(value = "courseID")Integer courseID,
                             @PathVariable(value = "topicID")Integer topicID,
                             Model model,HttpServletRequest request){
        return "showForumTopics";
    }



}
