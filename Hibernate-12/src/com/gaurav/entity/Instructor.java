package com.gaurav.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INSTRUCTOR")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_id_generator")
	@SequenceGenerator(name = "instructor_id_generator", allocationSize = 1, initialValue = 101, sequenceName = "instructor_id_sequ")
	@Column(name = "INSTRUCTOR_ID")
	private int instructorId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "INSTRUCTOR_DETAILS_ID")
	private InstructorDetails instructorDetails;

	@OneToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.EAGER,mappedBy="instructor")
	private List<Courses> courses;

	public Instructor() {
	}

	public Instructor(String firstName, String lastName, InstructorDetails instructorDetails) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.instructorDetails = instructorDetails;
	}

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public InstructorDetails getInstructorDetails() {
		return instructorDetails;
	}

	public void setInstructorDetails(InstructorDetails instructorDetails) {
		this.instructorDetails = instructorDetails;
	}
	
	public void addCourses(Courses tempCourse){
		if(courses==null){
			courses = new ArrayList<>();
		}
		courses.add(tempCourse);
		tempCourse.setInstructor(this);
	}

	@Override
	public String toString() {
		return "Instructor [instructorId=" + instructorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", instructorDetails=" + instructorDetails + "]";
	}
}
