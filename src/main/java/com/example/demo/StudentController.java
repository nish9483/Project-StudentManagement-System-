package com.example.demo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StudentController {
	@Autowired
	StudentService su;
	
	
	@GetMapping("/studentcollege")
	public String createPage()
	{
		return "create";
	}
     //post
	@PostMapping("/studentcollege")
    public String save(@ModelAttribute Student student)
    {
    	Student sn=su.saveStudent(student);
		return "redirect:/studentcollege";
    	
    }
	
	
	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable int id,Model m)
	{
		Student t=su.getStudentById(id);
		//m.addAttribute(t);
		return "edit";
	}
	
	//put mapping
	@PostMapping("/edit/{id}")
    public String update(@ModelAttribute Student student,@PathVariable int id)
    {
		student.setId(id);
    	Student st=su.updateStudent(student);
		return "redirect:/edit/{id}";
}
	
	//delete mapping
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id)
	{
		su.deleteStudent(id);
		return "redirect:/studentcollege";
	}
	

	
	//mapping
	
	@PostMapping("/student")
	public ResponseEntity<Student>savestudent(@RequestBody Student student)
	{
		Student r=su.saveStudent(student);
		return new ResponseEntity<Student>(r,HttpStatus.CREATED);
	}
	
	
	/*@GetMapping("/student")
	public ResponseEntity<List<Student>>getAllStudent()
	{
		List<Student> y=su.getAllStudent();
		return new ResponseEntity<List<Student>>(y,HttpStatus.OK);
	}*/
	@GetMapping("/student/{id}")
	public ResponseEntity<Student>getStudentById(@PathVariable int id)
	{
		Student f=su.getStudentById(id);
		return new ResponseEntity<Student>(f,HttpStatus.OK);
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student>updateStudent(@PathVariable int id,@RequestBody Student student)
	{
		student.setId(id);
		Student h=su.updateStudent(student);
		return new ResponseEntity<Student>(h,HttpStatus.OK);
	}
	@DeleteMapping("/student/{id}")
	public ResponseEntity <Void>deleteStudent(@PathVariable int id)
	{
		su.deleteStudent(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	//pagination
	
	@GetMapping("/student")
	public List<Student> getpageone()
	{
		Pageable pd=PageRequest.of(0, 4,Sort.by("id").ascending());
		Page<Student>g=su.findAll(pd);
		return g.getContent();
	}
	@GetMapping("/student/page2")
	public List<Student> getpagetwo()
	{
		Pageable pd=PageRequest.of(0 , 5,Sort.by("id").ascending());
		Page<Student>f=su.findAll(pd);
		return f.getContent();
	}
	
	
}
