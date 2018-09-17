package com.gaurav.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INSTRUCTOR_DETAILS")
public class InstructorDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_details_id_generator")
	@SequenceGenerator(name = "instructor_details_id_generator", allocationSize = 1, initialValue = 201, sequenceName = "instructor_details_id_sequ")
	@Column(name = "INSTRUCTOR_DETAILS_ID")
	private int instructorDetailsId;

	@Column(name = "HOBBY")
	private String hobby;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "instructorDetails")
	private Instructor instrcutor;

	public InstructorDetails() {
	}

	public InstructorDetails(String hobby) {
		this.hobby = hobby;
	}

	public int getInstructorDetailsId() {
		return instructorDetailsId;
	}

	public void setInstructorDetailsId(int instructorDetailsId) {
		this.instructorDetailsId = instructorDetailsId;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "InstructorDetails [instructorDetailsId=" + instructorDetailsId + ", hobby=" + hobby + "]";
	}
}
