package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.*;
import cn.lightina.managebooks.service.CourseService;
import cn.lightina.managebooks.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ForumController {

    @Autowired
    ForumService forumService;

    @Autowired
    CourseService courseService;

    // 点击课程进入该课程的讨论区，显示所有帖子
    @RequestMapping("/forum/{courseID}")
    public String forum(@PathVariable(value = "courseID")Integer courseID,
                             Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("courseID",courseID);

        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);

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

        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);

        List<Comment> commentList = forumService.getCommentByTopicID(topicID);
        List<List<ReComment>> reCommentListList = new ArrayList<List<ReComment>>();
        for(int i = 0;i < commentList.size();i++){
            Comment tempComment = commentList.get(i);
            Integer commentID = tempComment.getCommentID();
            List<ReComment> tempReCommentList = forumService.getReCommentByCommentID(commentID);

            reCommentListList.add(tempReCommentList);
        }
//        List<ReComment> reCommentList = reCommentListList.get(0);
//        ReComment reComment = reCommentList.get(0);
//        System.out.println(reComment.toString());

        model.addAttribute("commentList",commentList);
        model.addAttribute("reCommentListList",reCommentListList);

        return "forumShowTopic";
    }

    //添加编辑某课程讨论区的帖子
    @RequestMapping("/forum/{courseID}/editTopic")
    public String editForumTopic(@PathVariable(value = "courseID")Integer courseID, Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("courseID",courseID);

        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);
        return "forumEditTopic";
    }

    //添加某课程讨论区的帖子
    @RequestMapping("/forum/{courseID}/addTopic")
    public String addForumTopic(@PathVariable(value = "courseID")Integer courseID,
                                Model model,HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);

        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);

        Integer userID = user.getUserID();
        String tag = request.getParameter("tag");
        String title = request.getParameter("title");
        String abstracts = request.getParameter("abstracts");
        String text = request.getParameter("text");

        User addTopicUser = forumService.getUserByUserID(userID);

        Topic newTopic = new Topic(userID,tag,title,abstracts,text,courseID,addTopicUser.getUserName(),addTopicUser.getUserType());

        // System.out.println(newTopic);
        int flag = forumService.addTopic(newTopic);
        if(flag!=1){
            model.addAttribute("msg","创建话题失败！");
        }else{
            model.addAttribute("msg","创建话题成功！");
        }

        model.addAttribute("courseID",courseID);
        List<Topic> topicList = forumService.getTopicByCourseID(courseID);
        model.addAttribute("topicList",topicList);
        return "forum";
    }
    //删除某课程讨论区的帖子
    @RequestMapping("/forum/{courseID}/deleteTopic/{topicID}")
    public String deleteForumTopic(@PathVariable(value = "courseID")Integer courseID,
                                    @PathVariable(value = "topicID")Integer topicID,
                                   Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);

        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);

        int flag = forumService.deleteTopic(topicID);
        if(flag!=1){
            model.addAttribute("msg","删除话题失败！");
        }else{
            model.addAttribute("msg","删除话题成功！");
        }

        model.addAttribute("courseID",courseID);
        List<Topic> topicList = forumService.getTopicByCourseID(courseID);
        model.addAttribute("topicList",topicList);
        return "forum";
    }

    //评论
    @RequestMapping("/forum/{courseID}/topic/{topicID}/addComment")
    public String addForumComment(@PathVariable(value = "courseID")Integer courseID,
                                  @PathVariable(value = "topicID")Integer topicID,
                                  Model model,HttpServletRequest request){


        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        Topic topic = forumService.getTopicByTopicID(topicID);
        model.addAttribute("topic",topic);
        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);

        Integer userID = user.getUserID();
        String email = request.getParameter("email");
        String website = request.getParameter("website");
        String text = request.getParameter("text");
        User addCommentUser = forumService.getUserByUserID(userID);

        Comment newComment = new Comment(userID,topicID,text,email,website,addCommentUser.getUserName(),addCommentUser.getUserType());
        System.out.println(newComment.toString());
        int flag = forumService.addComment(newComment);
        if(flag!=1){
            model.addAttribute("msg","添加评论失败！");
        }else{
            model.addAttribute("msg","添加评论成功！");

            Topic temptopic = forumService.getTopicByTopicID(topicID);
            forumService.updateTopic(topicID,temptopic.getCommentCount()+1);
        }

        List<Comment> commentList = forumService.getCommentByTopicID(topicID);
        List<List<ReComment>> reCommentListList = new ArrayList<List<ReComment>>();
        for(int i = 0;i < commentList.size();i++){
            Comment tempComment = commentList.get(i);
            Integer commentID = tempComment.getCommentID();
            List<ReComment> tempReCommentList = forumService.getReCommentByCommentID(commentID);
            reCommentListList.add(tempReCommentList);
        }
        model.addAttribute("commentList",commentList);
        model.addAttribute("reCommentListList",reCommentListList);

        return "forumShowTopic";

    }
    //删除评论
    @RequestMapping("/forum/{courseID}/topic/{topicID}/comment/{commentID}/deleteComment")
    public String deleteForumComment(@PathVariable(value = "courseID")Integer courseID,
                                     @PathVariable(value = "topicID")Integer topicID,
                                     @PathVariable(value = "commentID")Integer commentID,
                                     Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);

        int flag = forumService.deleteComment(commentID);
        if(flag!=1){
            model.addAttribute("msg","删除评论失败！");
        }else{
            model.addAttribute("msg","删除评论成功！");
            Topic temptopic = forumService.getTopicByTopicID(topicID);
            forumService.updateTopic(topicID,temptopic.getCommentCount()-1);
        }


        Topic topic = forumService.getTopicByTopicID(topicID);
        model.addAttribute("topic",topic);
        List<Comment> commentList = forumService.getCommentByTopicID(topicID);
        List<List<ReComment>> reCommentListList = new ArrayList<List<ReComment>>();
        for(int i = 0;i < commentList.size();i++){
            Comment tempComment = commentList.get(i);
            Integer commentid = tempComment.getCommentID();
            List<ReComment> tempReCommentList = forumService.getReCommentByCommentID(commentid);
            reCommentListList.add(tempReCommentList);
        }
        model.addAttribute("commentList",commentList);
        model.addAttribute("reCommentListList",reCommentListList);

        return "forumShowTopic";

    }

    @RequestMapping("/forum/{courseID}/topic/{topicID}/comment/{commentID}/editReComment")
    public String editForumReComment(@PathVariable(value = "courseID")Integer courseID,
                                    @PathVariable(value = "topicID")Integer topicID,
                                    @PathVariable(value = "commentID")Integer commentID,
                                    Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("courseID",courseID);
        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);

        Topic topic = forumService.getTopicByTopicID(topicID);
        model.addAttribute("topic",topic);

        return "forumEditReComment";

    }

    @RequestMapping("/forum/{courseID}/topic/{topicID}/comment/{commentID}/addReComment")
    public String addForumReComment(@PathVariable(value = "courseID")Integer courseID,
                                    @PathVariable(value = "topicID")Integer topicID,
                                    @PathVariable(value = "commentID")Integer commentID,
                                    Model model,HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);

        Topic topic = forumService.getTopicByTopicID(topicID);
        model.addAttribute("topic",topic);
        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);


        Integer userID = user.getUserID();
        String email = request.getParameter("email");
        String website = request.getParameter("website");
        String text = request.getParameter("text");
        User addReCommentUser = forumService.getUserByUserID(userID);

        ReComment newReComment = new ReComment(commentID,userID,text,email,website,addReCommentUser.getUserName(),addReCommentUser.getUserType());
        System.out.println(newReComment.toString());

        int flag = forumService.addReComment(newReComment);
        if(flag!=1){
            model.addAttribute("msg","回复评论失败！");
        }else{
            model.addAttribute("msg","回复评论成功！");
        }

        System.out.println("debug1");

        List<Comment> commentList = forumService.getCommentByTopicID(topicID);
        List<List<ReComment>> reCommentListList = new ArrayList<List<ReComment>>();
        for(int i = 0;i < commentList.size();i++){
            Comment tempComment = commentList.get(i);
            Integer tempCommentID = tempComment.getCommentID();
            List<ReComment> tempReCommentList = forumService.getReCommentByCommentID(tempCommentID);
            reCommentListList.add(tempReCommentList);
        }
        model.addAttribute("commentList",commentList);
        model.addAttribute("reCommentListList",reCommentListList);

        System.out.println("debug2");

        return "forumShowTopic";
    }

    //查找某课程讨论区的帖子
    @RequestMapping("/forum/{courseID}/searchTopic/{title}")
    public String searchTopic(@PathVariable(value = "courseID")Integer courseID,@PathVariable(value = "title")String title, Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("courseID",courseID);
        CourseList course = courseService.findcourseByID(courseID);
        model.addAttribute("course",course);

        List<Topic> topicList = forumService.getTopicByTitle(courseID,title);
        model.addAttribute("topicList",topicList);
        return "forum";
    }
}
