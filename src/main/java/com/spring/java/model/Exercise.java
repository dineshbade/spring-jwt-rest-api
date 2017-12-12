package com.spring.java.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author By Dinesh Bade Nov 13, 2017
 *
 */
@Table(name="exercise")
@Entity
public class Exercise {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="exercise_id")
	private Long exerciseId;
	
	@Column(name="exercise_name")
	@NotEmpty
	private String exerciseName;
	
	
	@ManyToMany(mappedBy="exercises")
	private Set<Program> programs;


	public Long getExerciseId() {
		return exerciseId;
	}


	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}


	public String getExerciseName() {
		return exerciseName;
	}


	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}


	public Set<Program> getPrograms() {
		return programs;
	}


	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}
	
	
	
	
}
