package com.hk.repository;

import java.util.List;

import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hk.entity.Coments;

public interface Icommentsrepository extends JpaRepository<Coments, Integer>
{
	@Query("SELECT c FROM Coments c WHERE c.post.pid = ?1")
	List<Coments> showAllCommentsbyId(Integer id);


}
