package com.luv2code.springdemo.mvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.springdemo.entity.Instructor;
import com.luv2code.springdemo.entity.InstructorDetail;
import com.luv2code.springdemo.entity.Student;

public class CreateDemo {
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
			//create the objects
			Instructor tempInstructor = new Instructor("Mike","yooh","mike@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/user/luv2codetv",
					"Mike Code!!");
			
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
			factory.close();
		}
	}	
}