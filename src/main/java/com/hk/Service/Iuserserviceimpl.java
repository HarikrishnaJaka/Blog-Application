package com.hk.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.binding.Registerform;
import com.hk.binding.loginForm;
import com.hk.entity.User;
import com.hk.repository.Iuserrepository;

import jakarta.servlet.http.HttpSession;

@Service
public class Iuserserviceimpl  implements Iuserservice
{
	@Autowired
	private Iuserrepository ueserrepo;
	@Autowired
	private HttpSession session;
	
	@Override
	public boolean registerUser(Registerform form)
	{
		User user =ueserrepo.findByEmail(form.getEmail());
		if(user==null)
		{
			User user2=new User();
			BeanUtils.copyProperties(form, user2);
			ueserrepo.save(user2);
			return true;
		}
		
		return false;
	}
	@Override
	public String loginUser(loginForm form)
	{
		User user=ueserrepo.findByEmailAndPassword(form.getEmail(), form.getPassword());
		if(user==null)
		{
			return "invalid credentials";
		}
		session.setAttribute("userId", user.getUid());
		return "login success";
	}
	
	

}
