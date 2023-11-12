package com.practice.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.practice.sms.repository.StudentRepository;
import com.practice.sms.service.StudentService;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner{
	
	
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
		/*
		 * ConfigurableApplicationContext context =
		 * SpringApplication.run(StudentManagementSystemApplication.class, args);
		 * 
		 * StudentService st=context.getBean(StudentService.class);
		 * System.out.println(st.getAllStudents());;
		 */
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Student sd1= new Student("Shantesh","Hasabnis","shantesh1995@gmail.com");
		 * studentRepository.save(sd1);
		 * 
		 * Student sd2= new
		 * Student("Shripad","Gavaskar","shripadGavaskar1995@gmail.com");
		 * studentRepository.save(sd2);
		 */
		
		
	}

}
