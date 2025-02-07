package com.hk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hk.entity.Posts;

public interface Ipostsrepository extends JpaRepository<Posts, Integer>
{
	@Query("SELECT p FROM Posts p WHERE p.user.uid = ?1")
	public List<Posts> findAllPostsById(Integer id);
}
