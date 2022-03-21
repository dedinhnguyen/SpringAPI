package com.example.demo.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public StudentDTO updateStudent(long id, StudentDTO studentdto) {
		Student exitingStudent = studentrepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Student", "Id", id));
		if(exitingStudent != null) {
//		exitingStudent= convert.toEntity(studentdto);
		exitingStudent.setName(studentdto.getName());
		studentrepository.save(exitingStudent);
		StudentDTO studentdtoreponse= convert.toDTO(exitingStudent);
		return studentdtoreponse;
	}
		else {
			return null;
		}
	}

	@Override
	public void deleteStudentById(long id) {
		studentrepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Student","Id", id));
		studentrepository.deleteById(id);
	}

	@Override
	public Student findStudentById(long id) {
		return studentrepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Student","Id", id));
	}

	@Override
	public List<StudentDTO> findAll() {
		List<Student> student= studentrepository.findAll();
		List<StudentDTO> newstudentdto = convert.toListDTO(student);
		return newstudentdto;
	}

	@Override
	public StudentDTO saveStudent(StudentDTO studentdto) {
		Student studentrequest= convert.toEntity(studentdto);
		Student student = studentrepository.save(studentrequest);
		StudentDTO studentreponse= convert.toDTO(student);
		return studentreponse;
	}
	
	//sorting and paging
	public List<StudentDTO> findAllPagingAndSorting(int pageNum, int pageSize, String sort){
		List<StudentDTO> studentdto= new ArrayList<>();
	    Pageable pageable = PageRequest.of(pageNum - 1, pageSize,Sort.by(sort));
	    studentdto = convert.toPageDTO(pageable);
	    return studentdto;
	}

	@Override
	public List<StudentDTO> findAll(Pageable pageable) {
		List<StudentDTO> results = new ArrayList<>();
		List<Student> entities = studentrepository.findAll(pageable).getContent();
		results= convert.toListDTO(entities);
		return results;
	}

	@Override
	public void deleteAllStudent() {
		studentrepository.deleteAll();
	}
	
}
