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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hk.Service.CommentserviceImpl;
import com.hk.Service.Ipostsservice;
import com.hk.binding.Blogpost;
import com.hk.binding.Commentspost;
import com.hk.entity.Coments;
import com.hk.entity.Posts;

import jakarta.servlet.http.HttpSession;

@Controller
public class Postscontroller {
	@Autowired
	private Ipostsservice postserv;
     @Autowired
	private CommentserviceImpl comserv;
	@Autowired
	private HttpSession session;

	@GetMapping("/dashboard")
	public String dashBoard(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");

		model.addAttribute("posts", postserv.allPostsByuser(userId));
		return "dashboard";
	}

	@GetMapping("/addPost")
	public String addPostForm(@ModelAttribute("postform") Blogpost blogpost) {
		return "addPost";
	}

	@PostMapping("/addPosts")
	public String addPost(@ModelAttribute("postform") Blogpost blogpost, RedirectAttributes attrs) {
		String msg = postserv.addPost(blogpost);
		attrs.addFlashAttribute("resmsg", msg);
		return "redirect:dashboard";
	}

	@GetMapping("/posts")
	public String getPostById(@RequestParam("id") Integer id, Model model) {
		Optional<Posts> post = postserv.getPostByid(id);
		if (post.isPresent()) {
			model.addAttribute("comments", new Commentspost());
			model.addAttribute("post", post.get());
			session.setAttribute("posts", post.get().getPid());
			List<Coments> showAllCommentssById = comserv.ShowAllCommentssById(post.get().getPid());
			System.out.println(showAllCommentssById);
			System.out.println("Postscontroller.getPostById()");
			model.addAttribute("showcomment",showAllCommentssById);
			return "post-detail";
		} else {
			return "redirect:/posts";
		}
	}

	@GetMapping("/edit-post")
	public String showEditForm(@RequestParam("id") Integer id, Model model) {
		Optional<Posts> post = postserv.getPostByid(id);
		model.addAttribute("post", post);
		return "edit-post";
	}

	@PostMapping("/update-post")
	public String updatePost(@ModelAttribute("post") Posts post, Model model) {

		String msg = postserv.updatePost(post);
		model.addAttribute("resmsg", msg);

		return "redirect:dashboard";
	}

	@GetMapping("/delete-post")
	public String deletePost(@RequestParam("id") Integer id) {
		postserv.deletePost(id);
		return "redirect:dashboard";
	}

}
