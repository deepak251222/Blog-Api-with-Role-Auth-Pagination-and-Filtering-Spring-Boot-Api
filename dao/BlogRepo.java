package com.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Entity.Blog;
import com.spring.Entity.User;

@Repository
public interface BlogRepo extends JpaRepository<Blog, String>{
}
