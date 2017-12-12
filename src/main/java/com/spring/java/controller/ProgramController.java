package com.spring.java.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.java.model.Program;
import com.spring.java.model.User;
import com.spring.java.service.ProgramService;
import com.spring.java.util.RestResponse;

/**
 * @author By Dinesh Bade Nov 20, 2017
 *
 */
@RestController
@RequestMapping("/api")
public class ProgramController {
	
	@Autowired
	ProgramService programService;
	@RequestMapping(value = "/program", method = RequestMethod.POST 
			)
	public ResponseEntity<?> addProgram(@RequestBody Program program){
		try{
			
			Program p = programService.addProgram(program);
		
			if(p != null){
			 return  ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(new RestResponse(HttpStatus.CREATED.value(), p, "created"));

		}
		 return  ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body(new RestResponse(HttpStatus.CONFLICT.value(), null, "fail to add"));

		}catch(Exception  e){
			e.printStackTrace();
			 return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Internal server error"));
		}
		
	}
	
	@RequestMapping(value="/programs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllProgram(){
		
		try{
			
			List<Program> programs = programService.getAllProgram();
			 return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new RestResponse(HttpStatus.OK.value(), programs, "Ok"));
		}catch(Exception e){
			 return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, e.getMessage()));

		}
		
	}

}
