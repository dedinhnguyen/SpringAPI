package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.example.demo.dto.StudentDTO;
import com.example.demo.mapper.Convert;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
//	@Autowired
//	private ModelMapper modelMapper;
	@Autowired
	private Convert convert;
	
	@Autowired
	private StudentService studentservice;
	
	@PostMapping
	public ResponseEntity<StudentDTO> saveStudent(@Valid @RequestBody StudentDTO studentdto) {
		StudentDTO studentdtorequest = studentservice.saveStudent(studentdto);
		return new ResponseEntity<StudentDTO>(studentdtorequest, HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public List<StudentDTO> findAllStudent(){
		return studentservice.findAll();
	}
	@GetMapping("/page/{pageNum}")
	public List<StudentDTO> findAllStudent(@PathVariable int pageNum){
		return studentservice.findAllPagingAndSorting(pageNum);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") Long id) {
		Student student = studentservice.findStudentById(id);

		StudentDTO studentResponse = convert.toDTO(student);

		return ResponseEntity.ok().body(studentResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id, @RequestBody StudentDTO studentDto) {

		StudentDTO studentdtorequest = studentservice.saveStudent(studentDto);

		return ResponseEntity.ok().body(studentdtorequest);
	}
	@DeleteMapping("{id}")
	public List<StudentDTO> deleteStudent(@PathVariable(name = "id") Long id) {
		studentservice.deleteStudentById(id);
		return studentservice.findAll();
	}
}
