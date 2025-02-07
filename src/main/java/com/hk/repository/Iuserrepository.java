package com.hk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.entity.User;

public interface Iuserrepository  extends JpaRepository<User, Integer>
{
	public User findByEmail(String email); 
	public User findByEmailAndPassword(String email,String password);

}
