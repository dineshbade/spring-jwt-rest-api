package com.spring.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.java.dao.ProgramRepository;
import com.spring.java.model.Program;

/**
 * @author By Dinesh Bade Nov 20, 2017
 *
 */

@Service
public class ProgramServiceImpl implements ProgramService{

	@Autowired
	ProgramRepository programRepository;
	@Override
	public Program addProgram(Program program) {
		return programRepository.save(program);
	}
	@Override
	public List<Program> getAllProgram() {
		// TODO Auto-generated method stub
		return programRepository.findAll();
	}
	
	

}
