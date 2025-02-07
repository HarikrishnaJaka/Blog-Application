package com.hk.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="Blog_user")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer uid;
	private String firstName;
	private String lastName;
	private String email;
	private Long phno;
	private String password;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Posts> posts;

}
