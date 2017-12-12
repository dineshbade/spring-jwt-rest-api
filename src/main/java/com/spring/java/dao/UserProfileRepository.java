package com.spring.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.java.model.User;
import com.spring.java.model.UserProfilePicture;

/**
 * @author By Dinesh Bade Nov 10, 2017
 *
 */
@Transactional
public interface UserProfileRepository extends JpaRepository<UserProfilePicture, Long> {


	public UserProfilePicture findByUser(User user);

}
