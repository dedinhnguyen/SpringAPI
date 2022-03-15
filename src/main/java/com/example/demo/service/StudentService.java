package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Student;
public interface StudentService {
	Student saveStudent(Student lophoc);
	void deleteStudentById(long id);
	Student updateStudent(Student lophoc, long studentid);
	Student findStudentById(long studentid);
	List<Student> findAllStudent();
}
