package com.hk.Service;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Comments;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.binding.Commentspost;
import com.hk.entity.Coments;
import com.hk.entity.Posts;
import com.hk.repository.Icommentsrepository;
import com.hk.repository.Ipostsrepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CommentserviceImpl implements Icomentservice 
{
	@Autowired
	private Icommentsrepository commentRepo;
	@Autowired
	private Ipostsrepository postrepo;
	@Autowired
	private  HttpSession  session;
	
	
	@Override
	public Posts showPostById( Integer id)
	{
		Optional<Posts> opt = postrepo.findById(id);
		if(opt.isPresent()) {
			session.setAttribute("post", opt.get().getPid());
			return opt.get();
		}
		return null;
	}
	
	@Override
	public String addComment(Commentspost post) 
	{
		Coments comment=new Coments();
		BeanUtils.copyProperties(post, comment);
		Integer id = (Integer) session.getAttribute("posts");
		Optional<Posts> opt = postrepo.findById(id);
		comment.setPost(opt.get());
		commentRepo.save(comment);
		return "Your comment saved successfully";
	}

	@Override
	public List<Coments> ShowAllCommentssById(Integer id) 
	{
		
		return commentRepo.showAllCommentsbyId(id);
	}
	
	
	
	
	@Override
	public String editComment(Coments coments)
	{
		Optional<Coments>opt=commentRepo.findById(coments.getCid());
		if(opt.isPresent())
		{
			coments.setCommentAddedOn(opt.get().getCommentAddedOn());
			coments.setPost(opt.get().getPost());
			commentRepo.save(coments);
			return "Coment updated successfully";
		}
		else
		{
		return "Coment is not available";
		}
	}

	@Override
	public List<Coments> showAll()
	{
		
		return commentRepo.findAll();
	}
	@Override
	public Optional getCommentByid(Integer id)
	{	
	    return commentRepo.findById(id);
	}
	
	@Override
	public String deleteById(Integer id) 
	{
		Optional<Coments>opt=commentRepo.findById(id);
		if(opt.isPresent())
		{ 
			commentRepo.deleteById(id);
		 return "comment details are deleted";
	 }
	 else
	 {
		 return "coment is not available";
	 }
	}
	


	

}
