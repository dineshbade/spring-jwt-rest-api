/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.java.exception.ResourceNotFoundException;
import com.spring.java.model.User;
import com.spring.java.model.UserProfilePicture;
import com.spring.java.service.UserProfileService;
import com.spring.java.service.UserService;
import com.spring.java.util.RestResponse;

/**
 * @author User
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	UserProfileService userProfileService;

	@RequestMapping(value = "/countUser", method = RequestMethod.GET)
	public ResponseEntity<?> countTotalUser() {

		Long totalUser = userService.getTotalUser();

		return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(HttpStatus.OK.value(), totalUser, "success"));

	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {

		/* String name = userL.getUsername(); */ // get logged in username

		List<User> userList = userService.findAll();
		List<User> newUserList = new ArrayList<>();
		if (userList != null) {
			/*for (User user : userList) {
				user.add(linkTo(methodOn(UserController.class).getUserById(user.getUserId())).withSelfRel());

				newUserList.add(user);
			}7
*/
			/*
			*/

			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(userList);

		} else {
			throw new ResourceNotFoundException("User not found");
		}

	}
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id)  {
		System.out.println("here i am");


		User newUser = userService.findUserById(id);
		
		if(newUser!=null){
			/*newUser.add(linkTo(methodOn(HomeController.class).getUserById(newUser.getUserId())).withSelfRel());*/

/*			Link detail = linkTo(HomeController.class).getUserById(newUser.getUserId()).withSelfRel();
*/			
			 return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(newUser);
		
		}else{
			 throw new ResourceNotFoundException("User not found");
		}
		
	}
	@RequestMapping(value = "/userProfilePicture/{id}", method = RequestMethod.POST,headers = "Content-Type= multipart/form-data")
			
	public ResponseEntity<User> saveProfilePictureByUserId(@RequestPart("file") MultipartFile file,
			@PathVariable("id") Long id,HttpServletRequest request) throws IOException  {
		System.out.println("here i am");
		User newUser = userService.findUserById(id);
			System.out.println(file.getOriginalFilename());
			String prfilePicturePath =	saveProfilePictureByUserId(file,newUser,request);
				UserProfilePicture p = new UserProfilePicture();
				p.setPath(prfilePicturePath);
				p.setThumbnailPath("thumb_"+prfilePicturePath);
			
				p.setUser(newUser);
				userProfileService.saveProfilePicture(p);
				
				
		
		if(newUser!=null){
			/*newUser.add(linkTo(methodOn(HomeController.class).getUserById(newUser.getUserId())).withSelfRel());*/

/*			Link detail = linkTo(HomeController.class).getUserById(newUser.getUserId()).withSelfRel();
*/			
			 return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(newUser);
		
		}else{
			 throw new ResourceNotFoundException("User not found");
		}
		
	}

	private String saveProfilePictureByUserId(MultipartFile file, User newUser, HttpServletRequest request) throws IOException {

			String fileName = null;
			String saveDirectory =null;
			if(!file.isEmpty()){
			/*File newFile =	convert(file);*/
				String path = request.getRealPath("/");
				 saveDirectory = path+"/resources/Uploaded_Files/profile-picture/";
				 System.out.println(saveDirectory);
				 fileName = newUser.getUserId()+"-"+UUID.randomUUID();
				File destDirectory = new File(saveDirectory);
				fileName = fileName + "." + file.getOriginalFilename().split("\\.")[1].toLowerCase();
				if(!destDirectory.exists())
					destDirectory.mkdirs();
				BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
				 img.createGraphics().drawImage(ImageIO.read(convert(file)).getScaledInstance(128, 128, Image.SCALE_SMOOTH),0,0,null);
				
				 ImageIO.write(img, "jpg", new File(destDirectory+"/"  + "thumb_"+fileName));
			
				FileCopyUtils.copy(file.getBytes(), new FileOutputStream(destDirectory+"/"  + fileName));
			}
		
			return fileName;
	}
	public static File convert(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
}
