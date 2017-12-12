package com.spring.java.service;

import org.springframework.social.connect.UserProfile;

import com.spring.java.model.UserProfilePicture;

/**
 * @author By Dinesh Bade Nov 10, 2017
 *
 */
public interface UserProfileService {

	UserProfilePicture saveProfilePicture(UserProfilePicture p);
		
	/*public UserProfilePicture getUserProfileByUserId(Long userId);*/
}
