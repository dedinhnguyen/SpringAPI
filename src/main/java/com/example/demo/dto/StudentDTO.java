package com.example.demo.dto;

import lombok.Data;

@Data
public class StudentDTO {
	
	private String name;
	
	private String email;

	private String password;
	
	private String confirmpassword;
	
	private String address;
}