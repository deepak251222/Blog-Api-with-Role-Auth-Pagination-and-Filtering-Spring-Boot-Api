package com.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.PostResponse;
import com.spring.Entity.User;
import com.spring.Exception.UserNotFoundException;
import com.spring.dao.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	// ****** Add user *****
	public String addUser(User user) {
		userRepo.save(user);
		return "user add SuccessFull";
	}
 //// ******* pagination ******
	
	public PostResponse pagination(Integer pageNumber,Integer pageSize)
	{
	     Pageable p = PageRequest.of(pageNumber,pageSize);
	     
	     Page<User> pagePost=userRepo.findAll(p);
	     List<User> allPost=pagePost.getContent();
	     
		//Stream api used here for collection data 
	     
	  List<User> collect = allPost.stream().filter(i->i.getUid().startsWith("d")).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContext(collect);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotlaElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}
	
	// **** pagination with sorting ****
	public List<User> findProductsWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) {
		
	//Pageable p = PageRequest.of(pageNumber,pageSize,field);
		 
	    Page<User> user;
		try {
					user = userRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(field)));
					 List<User> allPost=user.getContent();
					 return allPost;
		} catch (Exception e) {
		
			e.printStackTrace();
		}
        return null; 
		
	}

	// *** sort by field product ** 
	 public List<User> findProductsWithSorting(String field){
		 try {
			 return  userRepo.findAll(Sort.by(Sort.Direction.ASC,field));
		} catch (Exception e) {
			e.printStackTrace();
		}
	       return null;
	    }
	
	 /// **** sorting by one field ***
//	 public List<String> sortingByone(String field) {
//		List<User> allPost =userRepo.findAll();
//		// List<User> collect = allPost.stream().filter(i->i.getUid().startsWith("d")).collect(Collectors.toList());
//		 List<User> collect = allPost.stream().sorted((i)->)
//	//	 List<String> name=userRepo.findAll(Sort.by(Sort.Direction.ASC,field));
//			return null;
//		}
	 
	 
	 
	// *** Get One User ****
	public User getOne(String uid) {
		User user = userRepo.getById(uid);
		try {
			if (user != null) {

				return user;
			} else {
				throw new UserNotFoundException("User Not Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new UserNotFoundException("User Not Found");
	}

	// ** For Login *****
	public User getUserNameAndUPass(String loginName, String loginpwd) {
		return userRepo.findByUemailAndUpwd(loginName, loginpwd);
	}
	// *** For Delete *****

	public String delete(User user) {
		try {
			userRepo.delete(user);
		} catch (Exception e) {

			throw new UserNotFoundException("user not found Exception");
		}
		return "delete is successfull";
	}

	// ****** Admin side all operation *****

	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	


}
