package com.luv2code.springdemo.mvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.springdemo.entity.Course;
import com.luv2code.springdemo.entity.Instructor;
import com.luv2code.springdemo.entity.InstructorDetail;

public class CreateCoursesDemo {
	public static void main(String Args[]) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create new session 
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction 
			session.beginTransaction();
			
			int theId = 1; 
			
			Instructor tempInstructor = session.get(Instructor.class,theId);
			
			Course tempCourse1 = new Course("Air Guitar - The ultimate guide");
			Course tempCourse2 = new Course("The pinball Masterclass");
			
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			//commit transaction
			session.getTransaction().commit();
			
		}finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}	
}