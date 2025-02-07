package com.hk.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hk.Service.Ipostsservice;
import com.hk.Service.Iuserservice;
import com.hk.binding.Registerform;
import com.hk.binding.loginForm;
import com.hk.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class Usercontroller
{
	@Autowired
	private Iuserservice serv;
	
	@Autowired
	private Ipostsservice postserv;
	@Autowired
	private HttpSession session;
	
	
	@GetMapping("/")
	public String indexPage(Model model)
	{
		model.addAttribute("posts", postserv.allPosts());
		return "index";
	}
	
	
	@GetMapping("/Register")
	public String registerpage(@ModelAttribute("regForm")User regform)
	{
		return "register";
	}
	
	@PostMapping("/Registerform")
	public String register(@ModelAttribute("regForm")Registerform form,RedirectAttributes attrs)
	{
		Boolean status=serv.registerUser(form);
		if(status)
		{
			attrs.addFlashAttribute("resmsg","Register successfully");
		}
		else
		{
			attrs.addFlashAttribute("errmsg", "This email already exhisted");
		}
		return "redirect:Register";
	}
	
	@GetMapping("/login")
	public String loginform(@ModelAttribute("user")loginForm form,Map<String, Object>map)
	{
		
		map.put("loginform", form);
		return "login";
	}
	@PostMapping("/loginuser")
	public String login(@ModelAttribute("user")loginForm form,RedirectAttributes attrs)
	{
		String loginmsg=serv.loginUser(form);
		if(loginmsg.contains("login success"))
		{
			
			return "redirect:dashboard";
		}
		attrs.addFlashAttribute("errormsg",loginmsg);
		return "redirect:login";
	}
	@GetMapping("/logout")
	public String logOut()
	{
		session.invalidate();
		return "redirect:/";
	}
	
	
	

}
