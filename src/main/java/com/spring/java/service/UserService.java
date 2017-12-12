/**
 * By Dinesh Sep 18, 2017
 */
package com.spring.java.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.spring.java.model.User;

/**
 * @author User
 *
 */
public interface UserService {
	
	public User save(User user);
	public List<User> findAll();
	public User findUserById(Long id) ;
	public Long getTotalUser();
   
}
