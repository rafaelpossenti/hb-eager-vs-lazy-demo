package com.luv2code.springdemo.mvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.springdemo.entity.Course;
import com.luv2code.springdemo.entity.Instructor;
import com.luv2code.springdemo.entity.InstructorDetail;

public class FetchJoinDemo {
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
			
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
									+ "JOIN FETCH i.courses "
									+ "where i.id=:theInstructorId", 
							Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			//return the query
			Instructor tempInstructor = query.getSingleResult();
			
			//get the data  Instructor|courses
			System.out.println("Instructor: " + tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
						
			// close the session
			session.close();
						
			System.out.println("Courses " + tempInstructor.getCourses());
		
			
			System.out.println("Done!");
			
		}finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}	
}