package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.ResourceNotFoundExeption;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private ModelMapper modelMapper;
	
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
		studentrepository.save(exitingStudent);
		return exitingStudent;
	}

	@Override
	public void deleteStudentById(long id) {
		studentrepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Student","Id", id));
		studentrepository.deleteById(id);
	}
	
	@Override
	public List<Student> findAllStudent() {
			return studentrepository.findAll();
	}

	@Override
	public Student findStudentById(long id) {
		return studentrepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Student","Id", id));
	}
	@Override
	public Student convertDTOToEntity(StudentDTO Studentdto) {
		Student Student= new Student();
		Student = modelMapper.map(Studentdto, Student.class);
		return Student;
		
	}

}
