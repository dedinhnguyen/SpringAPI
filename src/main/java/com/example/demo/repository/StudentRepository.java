package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
//@Query("SELECT p FROM Student p WHERE CONCAT(p.name, p.email, p.address) LIKE %?1%")
//public List<Student> search(String keyword);
}
