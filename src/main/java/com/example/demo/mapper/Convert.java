package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;

@Component
public class Convert {
	@Autowired
	private ModelMapper modelMapper;
	
	public Student toEntity(StudentDTO studentdto) {

		Student studentRequest = modelMapper.map(studentdto, Student.class);

		return studentRequest;
	}
	public StudentDTO toDTO(Student student) {
		StudentDTO studentRequest= modelMapper.map(student, StudentDTO.class);
				return studentRequest;
	}

}
