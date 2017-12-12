package com.spring.java.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author By Dinesh Bade Nov 10, 2017
 *
 */
@Entity
@Table(name="ProfilePicture")
public class UserProfilePicture {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_picture_id")
	private Long profilePictureId;
	
	@Column(name="path")
	private String path;
	
	@Column(name="thumbnail_path")
	private String thumbnailPath;
	@JsonIgnore
	@OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	
	
	public String getThumbnailPath() {
		return thumbnailPath;
	}


	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}


	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	public Long getProfilePictureId() {
		return profilePictureId;
	}
	public void setProfilePictureId(Long profilePictureId) {
		this.profilePictureId = profilePictureId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	

}
