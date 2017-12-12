package com.spring.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.java.model.Program;

/**
 * @author By Dinesh Bade Nov 20, 2017
 *
 */
@Transactional
public interface ProgramRepository extends JpaRepository<Program, Long>{

}
