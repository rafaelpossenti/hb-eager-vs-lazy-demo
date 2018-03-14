package com.luv2code.springdemo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
	
	
	public static void main(String[] args) {
		String DBURL = "jdbc:postgresql://localhost:5433/hb-01-one-to-one-uni";
		String USER = "postgres";
		String SENHA = "root";
		
		try {
			System.out.println("Connecting to the database");
			Connection conn = DriverManager.getConnection(DBURL, USER, SENHA);
			System.out.println("Conexão OK!");
		}catch(Exception ex) {
			ex.printStackTrace();

		}

	}

}
