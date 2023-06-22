package com.spring.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class Blog {
	@Id
	private String blog_id;
	private String blog_title;
	private String blog_desc;
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Blog(String blog_id, String blog_title, String blog_desc) {
		super();
		this.blog_id = blog_id;
		this.blog_title = blog_title;
		this.blog_desc = blog_desc;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_desc() {
		return blog_desc;
	}
	public void setBlog_desc(String blog_desc) {
		this.blog_desc = blog_desc;
	}
	
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;


}
