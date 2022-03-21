package com.example.demo.Config;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.StudentDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewOuput {
	private long page;
	private List<StudentDTO> listResult= new ArrayList<>();

}
