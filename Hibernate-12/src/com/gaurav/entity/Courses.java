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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COURSES")
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_generator")
	@SequenceGenerator(name = "course_id_generator", allocationSize = 1, initialValue = 301, sequenceName = "course_id_sequ")
	@Column(name = "COURSE_ID")
	private int courseId;

	@Column(name = "COURSE_NAME")
	private String courseName;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTRUCTOR_ID")
	private Instructor instructor;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID")
	private List<Review> reviews;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "courses_students", joinColumns = @JoinColumn(name = "COURSE_ID"), inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
	private List<Student> students;

	public Courses() {
	}

	public Courses(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public void addReview(Review tempReview) {
		if (reviews == null) {
			reviews = new ArrayList<>();
		}
		reviews.add(tempReview);
	}
	
	public void addStudent(Student tempStudent) {
		if (students == null) {
			students = new ArrayList<>();
		}
		students.add(tempStudent);
	}

	@Override
	public String toString() {
		return "Courses [courseId=" + courseId + ", courseName=" + courseName + "]";
	}

}
