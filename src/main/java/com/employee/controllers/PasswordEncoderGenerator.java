package com.employee.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
	public static void main(String[] args) {
		
		int i = 0;
		while(i < 10){
			String password = "123456";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			
			System.out.println(hashedPassword);
			i++;
			//it generate 10 hash values from this program and each time the value is different 
			//we get the first output and put into the database
			//that is $2a$10$QU2BZj6ZuGwe7LylLY/yYe8dBb4c5f016tQPaLiMTd3RnZ.uNFOam
		}
	}

}
