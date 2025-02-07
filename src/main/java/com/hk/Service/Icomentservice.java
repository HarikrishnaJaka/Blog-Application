package com.hk.Service;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Comments;

import com.hk.binding.Commentspost;
import com.hk.entity.Coments;
import com.hk.entity.Posts;

public interface Icomentservice 
{
	public Posts showPostById(Integer id);
	public String addComment(Commentspost post);
	public List<Coments>ShowAllCommentssById(Integer id);
	public List<Coments>showAll();
	public Optional<Posts>getCommentByid(Integer id);
	public String editComment(Coments coments);
	public String deleteById(Integer id);

}
