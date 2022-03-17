package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class StudentDTO {
	
	private String id;
	
	@NotBlank
	@Size(min = 2, message = "user name should have at least 2 characters")
	private String name;
	
	@NotBlank(message="email not empty")
	@Email(message= "Must enter correct email address")
	private String email;
	
	@NotBlank(message="Address cannot be empty")
	private String address;
	
}