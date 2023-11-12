package com.practice.sms.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.sms.entity.Student;
import com.practice.sms.service.StudentService;

@Controller
public class StudentControlller {

	private StudentService studentService;

	public StudentControlller(StudentService studentService) {
		super();
		this.studentService = studentService;

	}

	@GetMapping("/liststudents")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";

	}

	@PostMapping("/createstudents")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/liststudents";

	}

	@GetMapping("/students/edit/{id}")
	public String updateStudentForm(@PathVariable Long id, Model model) {

		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";

	}

	@PostMapping("/updatestudent/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setYear(student.getYear());
		studentService.updateStudent(existingStudent);

		return "redirect:/liststudents";
	}

	@GetMapping("/students/delete/{id}")
	public String deleteStudentForm(@PathVariable Long id, Model model) {
		studentService.deleteStudentByid(id);
		return "redirect:/liststudents";

	}

	@ResponseBody
	@RequestMapping("/studentsAPI")
	public List<Student> listStudentsAPI(Model model) {
		return studentService.getAllStudents();
	}

	@ResponseBody
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id) {
		Student student = studentService.getStudentById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "value1");
		return ResponseEntity.ok().headers(headers).body(student);
	}

	@ResponseBody
	@PostMapping("/saveStudent")
	public String studentSave(@RequestBody Student student) {
		student = studentService.saveStudent(student);
		return "student with id : " + student.getId() + " is saved";
	}

	@ResponseBody
	@GetMapping("/getAllStudentNames")
	public ResponseEntity<?> getAllStudentNames() {

		String names = studentService.getAllStudents().stream()
				.map(student -> student.getFirstName() + " " + student.getLastName()).collect(Collectors.joining(", "));

		return new ResponseEntity<>(names, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/getByStudentYear")
	public ResponseEntity<?> getByStudentYear() {

		Map<Integer, List<Student>> studentObj = studentService.getAllStudents().stream()
				.collect(Collectors.groupingBy(student -> student.getYear()));

		return new ResponseEntity<>(studentObj, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping("/getUniqueStudentsByStudentYear")
	public ResponseEntity<?> getUniqueStudentsByStudentYear() {

		// Map<String,Set<Student>>
		// studentMap=studentService.getAllStudents().stream().collect(Collectors.groupingBy(student->student.getYear(),Collectors.toSet()));

		Map<Integer, Set<Student>> studentObj = studentService.getAllStudents().stream()
				.collect(Collectors.groupingBy(student -> student.getYear(), TreeMap::new, Collectors.toSet()));

		return new ResponseEntity<>(studentObj, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping("/getMaxYear")
	public int getMaxYear() {

		// return
		// studentService.getAllStudents().stream().map(student->student.getYear()).max(Integer::compare).get();

		return studentService.getAllStudents().stream().map(student -> student.getYear()).mapToInt(year -> year)
				.summaryStatistics().getMax();

	}

	@ResponseBody
	@GetMapping("/getFilterStudentData")
	public List<Student> getFilterStudentData() {
		return studentService.getAllStudents().stream()
				.filter(std -> std.getEmail().equals("marakadyogesh@gmail.com") && std.getYear() > 2012)
				.collect(Collectors.toList());
	}

	@ResponseBody
	@GetMapping("/getSortedStudentData")
	public Map<String,Integer> getSortedStudentData() {
		/*return studentService.getAllStudents().stream()
				.sorted(((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()))).collect(Collectors.toList());*/

		//return studentService.getAllStudents().stream().filter(c->c.getYear()>2010).sorted((c1,c2)->(c1.getYear()).compareTo(c2.getYear())).collect(Collectors.toList());
		
		
		Map<String,Integer> myNameToYear= studentService.getAllStudents().stream().collect(Collectors.toMap(Student::getFirstName,Student::getYear,(old,newval)->old));
		return myNameToYear;
	}
}
