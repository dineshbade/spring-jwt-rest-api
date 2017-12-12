/**
 * By Dinesh Sep 15, 2017
 */
package com.spring.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.java.model.User;


/**
 * @author User
 *
 */



@Transactional

public interface UserRepository extends JpaRepository<User, Long>  {

	User findByEmail(String username);

	


	
	
	

}
