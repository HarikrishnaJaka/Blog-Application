package com.hk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.Service.CommentserviceImpl;
import com.hk.Service.Postserviceimpl;
import com.hk.binding.Commentspost;
import com.hk.entity.Coments;
import com.hk.entity.Posts;

import jakarta.servlet.http.HttpSession;

@Controller
public class Commentscontroller 
{
	@Autowired
	private CommentserviceImpl commentserv;
	@Autowired
	private Postserviceimpl postserv;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/comment")
	public String addComment(@ModelAttribute("comments")Commentspost comments,Model model)
	{
		String msg=commentserv.addComment(comments);
		model.addAttribute("resmsg",msg);
		Integer id = (Integer) session.getAttribute("posts");
		return "redirect:posts?id="+id;
		
	}
	
	
	
	@GetMapping("/post-details")
    public String showPostDetails(@RequestParam("id") Integer id, Model model)
    {
        Optional<Posts> post = postserv.getPostByid(id);
        if (post == null) {
            return "redirect:/";
        }

        session.setAttribute("post", id);  

        model.addAttribute("post", post);
        model.addAttribute("comments", commentserv.ShowAllCommentssById(id));

        return "redirect:posts";  
    }	
	
	@GetMapping("/showcomments")
	public String showComments(Model model)
	{
		List<Coments>coments=commentserv.showAll();
		model.addAttribute("comments",coments);
		return "allComments";
	}
	
	
	 @GetMapping("/edit-comment")
	    public String showEditForm(@RequestParam("id") Integer id, Model model) 
	 {
	        Optional<Coments> comment = commentserv.getCommentByid(id); 
	        model.addAttribute("comment", comment); 
	        return "edit-coment"; 
	    }
	 @PostMapping("/update-comment")
	    public String updatePost(@ModelAttribute("comment") Coments coments,Model model)
	 {
		 
		 String msg= commentserv.editComment(coments); 
		 model.addAttribute("resmsg", msg);
		 
	        return "redirect:showcomments";
	    }
	 
	 @GetMapping("/delete-comment")
	 public String deleteComment(@RequestParam("id")Integer id)
	 {
		 commentserv.deleteById(id);
		return "redirect:showcomments";
		 
	 }

}
