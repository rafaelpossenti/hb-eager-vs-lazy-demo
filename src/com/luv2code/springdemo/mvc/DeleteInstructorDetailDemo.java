package com.luv2code.springdemo.mvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.springdemo.entity.Instructor;
import com.luv2code.springdemo.entity.InstructorDetail;
import com.luv2code.springdemo.entity.Student;

public class DeleteInstructorDetailDemo {
	public static void main(String Args[]) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create new session 
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction 
			session.beginTransaction();
			
			//get the instructor detail object
			int theId = 3; 
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			//avoid an exception
			tempInstructorDetail.getInstructor().setInstructorDetail(null);;
			
			//delete the instructor and the detail instructor
			session.delete(tempInstructorDetail);
			
			
			
			//commit transaction
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}	
}