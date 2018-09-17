package com.gaurav.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gaurav.entity.Courses;
import com.gaurav.entity.Instructor;
import com.gaurav.entity.InstructorDetails;
import com.gaurav.entity.Review;
import com.gaurav.entity.Student;

public class Client {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class)
				.addAnnotatedClass(Courses.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(Review.class).buildSessionFactory();

		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			/**
			 * Step -1 Instructor and InstructorDetails Class Created and Data
			 * got populated
			 */
			
			session.save(new Instructor("Zack", "Anderson", new InstructorDetails("PUBG Game")));
			session.save(new Instructor("Alfie", "Dyes", new InstructorDetails("Movies")));
			session.save(new Instructor("Zoella", "Sugg", new InstructorDetails("Vlogging")));

			/**
			 * Step -2 Course Class created and data is added into that.
			 */

			Instructor instructor = session.createQuery("from Instructor where firstName='Zack'", Instructor.class)
					.getSingleResult();
			Courses c1 = new Courses("Java OCA-JP8");
			Courses c2 = new Courses("Java OCP-JP8");
			instructor.addCourses(c1);
			instructor.addCourses(c2);

			session.save(c1);
			session.save(c2);

			/**
			 * Step -3 Review Class creation
			 */

			Courses courses = session.createQuery("from Courses where courseName='Java OCP-JP8' ", Courses.class)
					.getSingleResult();

			Review r1 = new Review("Awesome Course");
			Review r2 = new Review("Thanks to Zack for this Course");
			Review r3 = new Review("I have scored 80% due to this course");

			courses.addReview(r1);
			courses.addReview(r2);
			courses.addReview(r3);

			session.save(r1);
			session.save(r2);
			session.save(r3);
			
			/**
			 *  Step -4 Student Class creation
			 * */
			Student one = new Student("Student1-firstName","Student1-lastName");
			Student two = new Student("Student2-firstName","Student2-lastName");
			courses.addStudent(one);
			courses.addStudent(two);
			
			session.save(one);
			session.save(two);
			

			session.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}

	}

}
