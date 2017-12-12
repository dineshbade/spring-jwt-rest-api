package com.spring.java.model;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.core.sym.Name;

/**
 * @author By Dinesh Bade Nov 13, 2017
 *
 */

@Table(name="program")
@Entity
public class Program {

	@Id
	@Column(name="program_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long programId;
	
	@Column(name="program_name")
	private String programName;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="program_exercise",joinColumns={@JoinColumn(name="program_id",referencedColumnName="program_id")},
			 inverseJoinColumns = {@JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id")})
	private Set<Exercise> exercises;
	
	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Set<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	
	
}
