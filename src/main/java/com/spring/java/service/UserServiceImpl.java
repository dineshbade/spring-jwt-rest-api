/**
 * By Dinesh Sep 18, 2017
 */
package com.spring.java.service;

import java.util.List;

import javax.xml.bind.ParseConversionEvent;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.java.dao.UserProfileRepository;
import com.spring.java.dao.UserRepository;
import com.spring.java.model.User;
import com.spring.java.model.UserProfilePicture;

/**
 * @author User
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;
	@Override
	public User save(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		try{
			List<User> users =  userRepository.findAll();
			return users;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		

	}

	@Override
	public User findUserById(Long id){
		User user =  userRepository.findOne(id);
		UserProfilePicture userPicture = userProfileRepository.findByUser(user);
		user.setProfilePicture(userPicture);
		return user;
	}

	@Override
	public Long getTotalUser() {
		
		return userRepository.count();
	}

	

	
	
}
