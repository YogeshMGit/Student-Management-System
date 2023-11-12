package com.practice.sms.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.sms.entity.Student;
import com.practice.sms.repository.StudentRepository;
import com.practice.sms.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> allStudentList = studentRepository.findAll();
		return allStudentList;

	}

	@Override
	public Student saveStudent(Student student) {

		student=studentRepository.save(student);
		System.out.println(student);
		return student;

	}

	@Override
	public Student getStudentById(Long id) {
		Student student = studentRepository.findById(id).get();
		return student;
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentByid(Long id) {
		studentRepository.deleteById(id);
	}
	
	/*SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session=sessionFactory.openSession();
	session.*/
	
	
	
	
	
}
