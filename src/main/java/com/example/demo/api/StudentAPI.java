package com.example.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public List<StudentDTO> getAllStudent() {

		return studentservice.findAllStudent().stream().map(student -> modelMapper.map(student, StudentDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") Long id) {
		Student student = studentservice.findStudentById(id);

		StudentDTO studentResponse = modelMapper.map(student, StudentDTO.class);

		return ResponseEntity.ok().body(studentResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id, @RequestBody StudentDTO studentDto) {

		Student studentRequest = modelMapper.map(studentDto, Student.class);

		Student student = studentservice.updateStudent(studentRequest, id);

		StudentDTO studentResponse = modelMapper.map(student, StudentDTO.class);

		return ResponseEntity.ok().body(studentResponse);
	}
}
