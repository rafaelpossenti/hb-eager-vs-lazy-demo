package com.luv2code.springdemo.mvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.springdemo.entity.Course;
import com.luv2code.springdemo.entity.Instructor;
import com.luv2code.springdemo.entity.InstructorDetail;

public class CreateInstructorDemo {
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
			//create the objects
			Instructor tempInstructor = new Instructor("Susan","Public","susan.public@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/susan",
					"Video games!");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction 
			session.beginTransaction();
			
			//NOTE: this will ALSO save the details object
			//because of CascadeType.ALL
			//save the instructor
			System.out.println("saving instructor" + tempInstructor);
			session.save(tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
		}finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}	
}