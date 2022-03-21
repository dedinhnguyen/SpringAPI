package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
public interface StudentService {
	Student saveStudent(Student student);
	void deleteStudentById(long id);
	
	Student findStudentById(long studentid);
	
	void deleteAllStudent();
	List<StudentDTO> findAll();
	StudentDTO saveStudent(StudentDTO studentdto);
	StudentDTO updateStudent(long studentid,StudentDTO studentdto);
	List<StudentDTO> findAllPagingAndSorting(int pageNum, int pageSize, String sort);
	
	List<StudentDTO> findAll(Pageable pageable);
}
