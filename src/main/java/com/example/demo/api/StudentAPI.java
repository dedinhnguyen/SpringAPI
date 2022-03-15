package com.example.demo.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentAPI {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentService studentservice;
	
	@PostMapping
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentdto) {

		Student StudentRequest = modelMapper.map(studentdto, Student.class);

		Student Student = studentservice.saveStudent(StudentRequest);

		StudentDTO postResponse = modelMapper.map(Student, StudentDTO.class);

		return new ResponseEntity<StudentDTO>(postResponse, HttpStatus.CREATED);
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
}
