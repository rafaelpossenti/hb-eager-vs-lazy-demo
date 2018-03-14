package com.luv2code.springdemo.mvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.springdemo.entity.Instructor;
import com.luv2code.springdemo.entity.InstructorDetail;
import com.luv2code.springdemo.entity.Student;

public class DeleteDemo {
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
			
			
			int theId = 1; 
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			if(tempInstructor != null) { 
				//Also delete associated details object
				session.delete(tempInstructor);
			}	
			
			//commit transaction
			session.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}	
}