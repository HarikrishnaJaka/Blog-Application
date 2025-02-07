package com.hk.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Blog_comments")
public class Coments 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cid;
	private String mail;
	private String name;
	@Lob
	private String content;
	
	@CreationTimestamp
	private LocalDateTime commentAddedOn;
	
	@UpdateTimestamp
	private LocalDateTime commentUpdatedOn;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "post id")
	private Posts post;




	@Override
	public String toString() {
		return "Coments [cid=" + cid + ", mail=" + mail + ", name=" + name + ", content=" + content
				+ ", commentAddedOn=" + commentAddedOn + ", commentUpdatedOn=" + commentUpdatedOn + "]";
	}
	
}
