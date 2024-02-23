package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepo sr;
	
	@Override
	public Student saveStudent(Student student)
	{
		
		return sr.save(student);
	}
	@Override
	public List<Student> getAllStudent()
	{
		return sr.findAll();
	}
	
	@Override
	public Student getStudentById(int id)
	{
		Optional<Student> s=sr.findById(id);
		//Student d=s.get();
		//or
		return s.get();
	}
	
	@Override
	public Student updateStudent(Student student)
	{
	Student st=sr.findById(student.getId()).get();
		st.setName(student.getName());
		st.setEmail(student.getEmail());
		st.setPhoneno(student.getPhoneno());
		Student r=sr.save(st);
		return r;
		
	}

	@Override
	public void deleteStudent(int id)
	{
		sr.deleteById(id);
		
	}
	@Override
	public Page<Student> findAll(Pageable p)
	{
		Page<Student>s= sr.findAll(p);
		return s;
	}
}



