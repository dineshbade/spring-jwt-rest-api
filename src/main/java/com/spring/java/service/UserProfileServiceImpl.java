package com.spring.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.spring.java.dao.UserProfileRepository;
import com.spring.java.model.UserProfilePicture;

/**
 * @author By Dinesh Bade Nov 10, 2017
 *
 */

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepository;
	/*@Override
	public UserProfilePicture getUserProfileByUserId(Long userId) {
			return userProfileRepository.findByUser(userId);
	}
*/

	@Override
	public UserProfilePicture saveProfilePicture(UserProfilePicture p) {
		return userProfileRepository.save(p);
	}
}
