package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Entity.Blog;
import com.spring.dao.BlogRepo;

@Service
public class BlogService {
	
	
	@Autowired
	private BlogRepo blogRepo;
	
	public void addBlog(Blog blog)
	{
		blogRepo.save(blog);
	}

}
