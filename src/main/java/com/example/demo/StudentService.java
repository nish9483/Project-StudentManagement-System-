package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
	Student saveStudent(Student student);
	List<Student> getAllStudent();

	Student getStudentById(int id);
	Student updateStudent(Student student);
	void deleteStudent(int id);
    Page<Student> findAll(Pageable p);
	

}
