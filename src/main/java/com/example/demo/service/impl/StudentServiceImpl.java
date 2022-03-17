package com.example.demo.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.ResourceNotFoundExeption;
import com.example.demo.mapper.Convert;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private Convert convert;
	
	@Autowired
	private StudentRepository studentrepository;
	
	@Override
	public Student saveStudent(Student student) {
		return studentrepository.save(student);
	} 

	@Override
	public Student updateStudent(Student student, long id) {
		Student exitingStudent = studentrepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Student", "Id", id));
		exitingStudent.setName(student.getName());
		exitingStudent.setEmail(student.getEmail());
		exitingStudent.setAddress(student.getAddress());
		studentrepository.save(exitingStudent);
		return exitingStudent;
	}

	@Override
	public void deleteStudentById(long id) {
		studentrepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Student","Id", id));
		studentrepository.deleteById(id);
	}
	
//	@Override
//	public List<Student> findAllStudent() {
//		return studentrepository.findAll();
//	}

	@Override
	public Student findStudentById(long id) {
		return studentrepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Student","Id", id));
	}

	@Override
	public List<StudentDTO> findAll() {
		List<StudentDTO> studentdto= new ArrayList<>();
		List<Student> student = studentrepository.findAll();
		for(Student item: student) {
			StudentDTO newstudendto= convert.toDTO(item);
			studentdto.add(newstudendto);
		}
		return studentdto;
	}

	@Override
	public StudentDTO saveStudent(StudentDTO studentdto) {
		Student studentrequest= convert.toEntity(studentdto);
		Student student = studentrepository.save(studentrequest);
		StudentDTO studentreponse= convert.toDTO(student);
		return studentreponse;
	}
	
	public List<StudentDTO> findAllPagingAndSorting(int pageNum) {
		List<StudentDTO> studentdto= new ArrayList<>();
		Sort sort = Sort.by("id").ascending();
	    int pageSize = 2;
	    Pageable pageable = PageRequest.of(pageNum - 1, pageSize,sort);
	    Page<Student> page = studentrepository.findAll(pageable);
	    for(Student item: page) {
			StudentDTO newstudendto= convert.toDTO(item);
			studentdto.add(newstudendto);
		}
	    return studentdto;
	}
}
