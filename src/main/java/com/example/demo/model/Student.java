package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name="student")
public class Student{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(name="name", nullable = false)
	@NotEmpty
	@Size(min = 2, message = "user name should have at least 2 characters")
	private String name;
	
	@Column(name="email",nullable = false)
	@NotEmpty(message="email not empty")
	@Email(message= "Must enter correct email address")
	private String email;
	
	@Column(name="password", nullable = false)
	@Length(min=8, max=32, message="Password must be 8-32 characters long")
	private String password;
	
	@Column(name="confirmpassword", nullable = false)
	private String confirmpassword;
	
	@Column(name="address", nullable = false)
	@NotEmpty(message="Address cannot be empty")
	private String address;
}