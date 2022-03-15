
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentService studentservice;
	
	@PostMapping
	public ResponseEntity<Student> saveStudent(@RequestBody Student Student){
		return new ResponseEntity<Student>(studentservice.saveStudent(Student), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Student> findAllStudent(){
		return studentservice.findAllStudent();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") long StudentId){
		studentservice.deleteStudentById(StudentId);
		return new ResponseEntity<String>("Xoa thanh cong! ", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Student> findStudentById(@PathVariable("id") long StudentId){
		return new ResponseEntity<Student>(studentservice.findStudentById(StudentId), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudentById(@PathVariable("id") long studentId, @RequestBody Student student){
		return new ResponseEntity<Student>(studentservice.updateStudent(student,studentId), HttpStatus.OK);
	}
}