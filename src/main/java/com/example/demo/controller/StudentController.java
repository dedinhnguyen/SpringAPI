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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config.NewOuput;
import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.ResourceNotFoundExeption;
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
		try {
		StudentDTO studentdtorequest = studentservice.saveStudent(studentdto);
		return new ResponseEntity<StudentDTO>(studentdtorequest, HttpStatus.CREATED);
		}
		catch(Exception e) {
		return new ResponseEntity<>(null, HttpStatus.CREATED);
		}
	}
	
	@GetMapping
	public List<StudentDTO> findAllStudent(){
		return studentservice.findAll();
		
	}
	
	@GetMapping("/page/")
	public NewOuput showNew(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam String sort) {
		NewOuput result= new NewOuput();
		result.setPage(page);
		result.setListResult(studentservice.findAllPagingAndSorting(page,limit, sort));
		return result;
	}
     
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") Long id) throws ResourceNotFoundExeption {
		try {
		Student student = studentservice.findStudentById(id);

		StudentDTO studentResponse = convert.toDTO(student);

		return ResponseEntity.ok().body(studentResponse);
	}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id, @RequestBody StudentDTO studentDto) throws ResourceNotFoundExeption {
		try {
			
		StudentDTO studentdtorequest = studentservice.updateStudent(id,studentDto);

		return ResponseEntity.ok().body(studentdtorequest);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAllStudent(){
		try {
		studentservice.deleteAllStudent();
		return new ResponseEntity<>(HttpStatus.OK);
	}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable(name = "id") Long id){
		try {
			
		studentservice.deleteStudentById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
