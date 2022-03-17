package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
public interface StudentService {
	Student saveStudent(Student student);
	void deleteStudentById(long id);
	Student updateStudent(Student lophoc, long studentid);
	Student findStudentById(long studentid);
//	List<Student> findAllStudent();
	
	
	List<StudentDTO> findAll();
	StudentDTO saveStudent(StudentDTO studentdto);
	List<StudentDTO> findAllPagingAndSorting(int pageNum);
}
