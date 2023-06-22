package com.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Entity.User;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, String> {
	
//	List<User> findByUemail(String uemail);
//	List<User> findByUpwd(String upwd);
	User findByUemailAndUpwd(String uemail,String Upwd);
    
}
     