package com.practice.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.sms.entity.Student;

@Service
public interface StudentService {
	
	public List<Student> getAllStudents();
	
	Student saveStudent(Student student);

	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	
	void deleteStudentByid(Long id);
}
