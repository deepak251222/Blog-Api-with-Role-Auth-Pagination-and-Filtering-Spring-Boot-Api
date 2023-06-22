package com.spring.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class User {
	@Id
	private String uid;
	private String uname;
	private String uemail;
	private String upwd;
//		
//		 @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})   //user coming from blog class 
//	     private List<Blog> blogAdd =new ArrayList<>();

	@OneToMany(targetEntity = Blog.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "uis_fk", referencedColumnName = "uid")
	private List<Blog> blogAdd;

	public User() {
		super();
	}
	public User(String uid, String uname, String uemail, String upwd, List<Blog> blogAdd) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.uemail = uemail;
		this.upwd = upwd;
		this.blogAdd = blogAdd;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public List<Blog> getBlogAdd() {
		return blogAdd;
	}

	public void setBlogAdd(List<Blog> blogAdd) {
		this.blogAdd = blogAdd;
	}

}
