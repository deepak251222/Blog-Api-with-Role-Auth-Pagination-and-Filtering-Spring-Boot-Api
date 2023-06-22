package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Entity.User;
import com.spring.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	// *** finding all User ***
	
	@GetMapping("/alluser")
	public List<User> getAll() {
		
				List<User> users=userService.getAllUser();
				if(users.size()>=1)
				{
					return users;
				}
				throw new UsernameNotFoundException("Not Found");
	}

	// ** Find by user ****
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable String id) throws Exception {
		if (id != null) {
			User user = userService.getOne(id);
			if (user != null) {
				return ResponseEntity.of(Optional.of(user));
			}

		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found User");
	}

	// **** Delete user ***
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) throws Exception {
		if (id != null) {
			User user = userService.getOne(id);
			if (user != null) {
				String str = userService.delete(user);
				return ResponseEntity.status(HttpStatus.OK).body(str);
			}
			else
			{
				throw new UsernameNotFoundException("Not Found User : ");
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found User");

	}
	
	//*** for Updating User ****
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody User userDetails) throws Exception{
		
		User user = userService.getOne(userDetails.getUid());
		if(user==null)
		{
			throw new UsernameNotFoundException("User Not Founnd");
		}
		else if (user != null) {
			user.setUid(userDetails.getUid());
			user.setUemail(userDetails.getUemail());
			user.setUname(userDetails.getUname());
			user.setBlogAdd(userDetails.getBlogAdd());
			userService.addUser(user);
			return ResponseEntity.of(Optional.of(user)).ok("user Update successFull");
			}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Avilable");
		
	}
	
}
