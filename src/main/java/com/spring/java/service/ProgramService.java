package com.spring.java.service;

import java.util.List;

import com.spring.java.model.Program;

/**
 * @author By Dinesh Bade Nov 20, 2017
 *
 */
public interface ProgramService {
	public Program addProgram(Program program);

	public List<Program> getAllProgram();

}
