package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.PostResponse;
import com.spring.Entity.Blog;
import com.spring.Entity.User;
import com.spring.Entity.UserLogin;
import com.spring.Exception.UserNotFoundException;
import com.spring.service.BlogService;
import com.spring.service.UserService;

import ch.qos.logback.core.status.Status;

@RestController

@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	// **** for Pagination *******

	@PostMapping("/allpage")
	public ResponseEntity<PostResponse> getPagination(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,

			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {

		PostResponse pagination = userService.pagination(pageNumber, pageSize);

		return new ResponseEntity<PostResponse>(pagination, HttpStatus.OK);
	}

	// ***** Sorting by Field ****
	@GetMapping("/sortby/{field}")
	public ResponseEntity<List<User>> sortByField(@PathVariable("field") String field) {
		List<User> findProductsWithSorting = userService.findProductsWithSorting(field);
		return new ResponseEntity<List<User>>(findProductsWithSorting, HttpStatus.OK);
	}
	
	// **** field By showing into page *******
	  @GetMapping("/allpagesorting")
	    public ResponseEntity<List<User>> getProductsWithPaginationAndSort(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
	    		
				@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,@RequestParam String field) {
	        List<User> productsWithPagination = userService.findProductsWithPaginationAndSorting(pageNumber, pageSize, field);
	        return  new ResponseEntity<List<User>>(productsWithPagination,HttpStatus.OK);
	    }
	
//      // **** Searching by Field ****
//	  @GetMapping("/sortbyfield{field}")
//	  public  ResponseEntity<?> searching(@PathVariable String field)
//	  {
//		     List<String> name=userService.sortingByone(field);
//		  return ResponseEntity.of(Optional.of(name),HttpStatus.OK);
//	  }
	 

// *** Adding Data Into *** 
	@PostMapping("/add")
	public String add(@RequestBody User user) {
		String str = userService.addUser(user);
		// blogService.addBlog(blog);

		return "User Add Blog  SuccessFull";
	}
	// ***Data into data base On id ***

	@GetMapping("/{uid}")
	public ResponseEntity<Object> getOne(@PathVariable String uid) throws Exception {
		User user = null;
		if (uid == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND).ok("not found");
		}
		user = userService.getOne(uid);
		if (user == null) {
			throw new UserNotFoundException("user not fount");

		} else {
			return new ResponseEntity<Object>(user, HttpStatus.OK);

		}

	}
	// *** user Loging pages ****

	@PostMapping("/userlogin")
	public ResponseEntity<Object> loginUser(@RequestBody UserLogin userLogin) {

		String username = userLogin.loginame;
		String userpassword = userLogin.loginpwd;
		User user = userService.getUserNameAndUPass(username, userpassword);
		if (user != null) {
			User u1 = userService.getUserNameAndUPass(username, userpassword);

			return new ResponseEntity<Object>(u1, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	// *** Deleting user base on username and password
	@DeleteMapping("/delete")
	public String delete(@RequestBody UserLogin userLogin) {
		String message = "";
		String username = userLogin.loginame;
		String userpassword = userLogin.loginpwd;
		User user = userService.getUserNameAndUPass(username, userpassword);
		if (user != null) {
			message = userService.delete(user);
		} else {
			message = "User don't Exist";
		}

		return message;

	}

	@PutMapping("/update")
	public ResponseEntity<User> update(@RequestBody User userDetails) {
		String message = "";
		User u1 = null;
		String username = userDetails.getUemail();
		String userpassword = userDetails.getUpwd();
		User user = userService.getUserNameAndUPass(username, userpassword);
		System.out.print(user);
		if (user != null) {
			user.setUid(userDetails.getUid());
			user.setUemail(userDetails.getUemail());
			user.setUname(userDetails.getUname());
			user.setBlogAdd(userDetails.getBlogAdd());
			userService.addUser(user);

			return new ResponseEntity<>(user, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		// return new ResponseEntity<>("message user",HttpStatus.BAD_GATEWAY).ok(null);

	}

}
