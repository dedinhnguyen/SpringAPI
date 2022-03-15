package com.example.demo.service;

import java.util.List;


import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
public interface StudentService {
	Student saveStudent(Student lophoc);
	void deleteStudentById(long id);
	Student convertDTOToEntity(StudentDTO studentdto);
	Student updateStudent(Student lophoc, long studentid);
	Student findStudentById(long studentid);
	List<Student> findAllStudent();
}
