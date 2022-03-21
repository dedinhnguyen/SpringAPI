package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Component
public class Convert {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentRepository studentrepository;
	
	public Student toEntity(StudentDTO studentdto) {

		Student studentRequest = modelMapper.map(studentdto, Student.class);

		return studentRequest;
	}
	public StudentDTO toDTO(Student student) {
		StudentDTO studentRequest= modelMapper.map(student, StudentDTO.class);
				return studentRequest;
	}

	public List<StudentDTO> toListDTO(List<Student> entities){
		List<StudentDTO> studentRequest= new ArrayList<>();
		entities = studentrepository.findAll();
		for(Student item: entities) {
			StudentDTO newstudendto= this.toDTO(item);
			studentRequest.add(newstudendto);
		}
		return studentRequest;
	}
	public List<StudentDTO> toPageDTO(Pageable pageable){
		List<StudentDTO> studentRequest= new ArrayList<>();
	    Page<Student> page = studentrepository.findAll(pageable);
	    for(Student item: page) {
			StudentDTO newstudendto= this.toDTO(item);
			studentRequest.add(newstudendto);
		}
	    return studentRequest;
	}
}
