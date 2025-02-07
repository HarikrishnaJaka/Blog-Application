package com.hk.Service;

import java.util.List;
import java.util.Optional;

import com.hk.binding.Blogpost;
import com.hk.entity.Posts;

public interface Ipostsservice 
{
	public List<Posts>allPosts();
	public List<Posts>allPostsByuser(Integer id);
	public String addPost(Blogpost blogpost);
	public Optional<Posts>getPostByid(Integer id);
	public String updatePost(Posts edpost);
	public String deletePost(Integer id);
	

}
