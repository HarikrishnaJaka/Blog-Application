package com.hk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.binding.Blogpost;
import com.hk.entity.Posts;
import com.hk.entity.User;
import com.hk.repository.Ipostsrepository;
import com.hk.repository.Iuserrepository;

import jakarta.servlet.http.HttpSession;

@Service
public class Postserviceimpl implements Ipostsservice {
	@Autowired
	private Ipostsrepository postrepo;
	@Autowired
	private Iuserrepository userRepo;
	@Autowired
	private HttpSession session;

	@Override
	public List<Posts> allPostsByuser(Integer id) {

		return postrepo.findAllPostsById(id);
	}

	@Override
	public String addPost(Blogpost blogpost) {
		Posts post = new Posts();
		BeanUtils.copyProperties(blogpost, post);
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "User is not logged in";
		}
		post.setUser(userRepo.findById(userId).get());
		postrepo.save(post);
		return "Your post is saved successfully";
	}

	@Override
	public Optional<Posts> getPostByid(Integer id) {
		List<Posts> posts = postrepo.findAll();
		return posts.stream().filter(post -> post.getPid().equals(id)).findFirst();
	}

	@Override
	public String updatePost(Posts edposts) {
		Optional<Posts> opt = postrepo.findById(edposts.getPid());
		if (opt.isPresent()) {
			edposts.setPostCreatedon(opt.get().getPostCreatedon());
			Integer userId = (Integer) session.getAttribute("userId");
			Optional<User> user=userRepo.findById(userId);
			edposts.setUser(user.get());
			System.out.println(opt.get());
			postrepo.save(edposts);
			return "post details are updated";
		} else {
			return "post not available";
		}
	}

	@Override
	public String deletePost(Integer id) {
		Optional<Posts> opt = postrepo.findById(id);
		if (opt.isPresent()) {
			postrepo.deleteById(id);
			return "post details are deleted";
		} else {
			return "post is not available";
		}

	}

	@Override
	public List<Posts> allPosts() {

		return postrepo.findAll();
	}

}
