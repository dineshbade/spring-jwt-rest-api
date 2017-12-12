/**
 * By Dinesh Oct 12, 2017
 */
package com.spring.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.spring.java.exception.ResourceNotFoundException;
import com.spring.java.model.User;
import com.spring.java.service.UserService;
import com.spring.java.util.AuthenticatedUser;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author User
 *
 */
@RestController
@RequestMapping("")
public class HomeController {

	@Autowired
	UserService userService;
	@RequestMapping(value = "/custom", method = RequestMethod.POST)
    public String custom() {
        return "custom";
    }
	private static final String TEMPLATE = "Hello, %s!";
	@RequestMapping(value = "/api/hello", method = RequestMethod.GET)
	public ResponseEntity<?> getSecure()  {
		System.out.println("here i am secure");
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      String name = user.getUsername(); //get logged in username
		 return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id)  {
		System.out.println("here i am");


		User newUser = userService.findUserById(id);
		if(newUser!=null){
			newUser.add(linkTo(methodOn(HomeController.class).getUserById(newUser.getUserId())).withSelfRel());

/*			Link detail = linkTo(HomeController.class).getUserById(newUser.getUserId()).withSelfRel();
*/			
			 return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(newUser);
		
		}else{
			 throw new ResourceNotFoundException("User not found");
		}
		
	}
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser()  {
	
	     /* String name = userL.getUsername();*/ //get logged in username

		List<User> userList = userService.findAll();
		List<User> newUserList = new ArrayList<>();
		if(userList!=null){
			for(User user : userList){
			
				/*user.add(linkTo(methodOn(HomeController.class).getUserById(user.getUserId())).withSelfRel());
		        HttpHeaders responseHeaders = new HttpHeaders();
		       */ newUserList.add(user);
			}
			
			
			/*
*/
			
			 return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(newUserList);
		
		}else{
			 throw new ResourceNotFoundException("User not found");
		}
		
	}
	@RequestMapping(value = "/user", method = RequestMethod.POST 
			)
	public ResponseEntity<User> addUser(@RequestBody User user){
		try{
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encriptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encriptedPassword);
		System.out.println(encriptedPassword);
		user.setRegistrationDate(new Date());
		User newUser = userService.save(user);
		if(newUser != null){
			newUser.setPassword(null);
			 return  ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(newUser);

		}
		 return  ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body(null);

		}catch(Exception  e){
			e.printStackTrace();
			 return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(null);
		}
		
	}
	@RequestMapping(value = "/api/user", method = RequestMethod.POST 
			)
	public ResponseEntity<User> addAdminUser(@RequestBody User user){
		try{
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encriptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encriptedPassword);
		System.out.println(encriptedPassword);
		user.setRegistrationDate(new Date());
		User newUser = userService.save(user);
		if(newUser != null){
			newUser.setPassword(null);
			 return  ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(newUser);

		}
		 return  ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body(null);

		}catch(Exception  e){
			e.printStackTrace();
			 return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(null);
		}
		
	}

	
}
