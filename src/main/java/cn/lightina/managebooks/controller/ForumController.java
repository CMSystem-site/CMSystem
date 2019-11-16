package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.Topic;
import cn.lightina.managebooks.pojo.User;
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
        Integer courseID = 1;
        List<Topic> topiclist = forumService.getTopicByCourseID(courseID);
        model.addAttribute("topic", topiclist.get(0));
        return "forumSingle";
    }

    @GetMapping("/forum/page")
    public String forumPage(){
        return "forumaddtemp";
    }



    @GetMapping("/forum/addMarkdown")
    public String addForumMarkdown(Model model,HttpServletRequest request){

        String text = request.getParameter("text");

        // Topic topic = new Topic(text);
//        Topic topic = new Topic();
//
//        int flag = forumService.addTopic(topic);
//        if(flag!=1){
//            model.addAttribute("msg","创建话题失败！");
//        }else{
//            model.addAttribute("msg","创建话题成功！");
//        }
//        System.out.println(topic.toString());
        return "forumEditMarkdown";
    }

    @GetMapping("/forum/showMarkdown")
    public String showForumMarkdown(Model model,HttpServletRequest request){
        Integer courseID = 1;
        List<Topic> topiclist = forumService.getTopicByCourseID(courseID);
        model.addAttribute("topic", topiclist.get(0));
        return "forumShowMarkdown";
    }


    // 点击课程进入该课程的讨论区，显示所有帖子
    @RequestMapping("/forum/{courseID}")
    public String forum(@PathVariable(value = "courseID")Integer courseID,
                             Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("courseID",courseID);
        List<Topic> topicList = forumService.getTopicByCourseID(courseID);
        model.addAttribute("topicList",topicList);
        return "forum";
    }

    // 查看某课程讨论区的帖子
    @RequestMapping("/forum/{courseID}/topic/{topicID}")
    public String showForumTopic(@PathVariable(value = "courseID")Integer courseID,
                             @PathVariable(value = "topicID")Integer topicID,
                             Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);

        Topic topic = forumService.getTopicByTopicID(topicID);
        model.addAttribute("topic",topic);
        return "forumShowTopic";
    }

    //添加编辑某课程讨论区的帖子
    @RequestMapping("/forum/{courseID}/editTopic")
    public String editForumTopic(@PathVariable(value = "courseID")Integer courseID, Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("courseID",courseID);

        return "forumEditTopic";
    }

    //添加某课程讨论区的帖子
    @RequestMapping("/forum/{courseID}/addTopic")
    public String addForumTopic(@PathVariable(value = "courseID")Integer courseID,
                                Model model,HttpServletRequest request){

        System.out.println("debug");
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);

        Integer userID = user.getUserID();
        System.out.println(userID);
        String tag = request.getParameter("tag");
        System.out.println(tag);
        String title = request.getParameter("title");
        String abstracts = request.getParameter("abstracts");
        String text = request.getParameter("text");



        Topic newtopic = new Topic(userID,tag,title,abstracts,text,courseID);
        System.out.println(newtopic);

        int flag = forumService.addTopic(newtopic);
        if(flag!=1){
            model.addAttribute("msg","创建话题失败！");
        }else{
            model.addAttribute("msg","创建话题成功！");
        }
        System.out.println(newtopic.toString());

        model.addAttribute("courseID",courseID);
        List<Topic> topicList = forumService.getTopicByCourseID(courseID);
        model.addAttribute("topicList",topicList);
        return "forum";
    }



}
