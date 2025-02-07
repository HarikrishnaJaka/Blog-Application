package com.hk.Service;

import com.hk.binding.Registerform;


import com.hk.binding.loginForm;

public interface Iuserservice 
{
	public boolean registerUser(Registerform from);
	public String loginUser(loginForm form);

}
