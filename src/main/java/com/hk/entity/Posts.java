package com.hk.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Blog_posts")
public class Posts 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pid;
	private String title;
	private String description;
	@Lob
	private String content;
	@CreationTimestamp
	private LocalDateTime postCreatedon;
	
	@UpdateTimestamp
	private LocalDateTime postUpdateon;
	
	
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Coments> comments;
	
	@ManyToOne
	@JoinColumn(name = "user id")
	private User user;

	@Override
	public String toString() {
		return "Posts [pid=" + pid + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", postCreatedon=" + postCreatedon + ", postUpdateon=" + postUpdateon + ", comments=" + comments
				+ ", user=" + user + "]";
	}

	
	
	
}
