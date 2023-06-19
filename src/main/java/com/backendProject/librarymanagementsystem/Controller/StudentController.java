package com.backendProject.librarymanagementsystem.Controller;

import com.backendProject.librarymanagementsystem.DTO.StudentRequestDto;
import com.backendProject.librarymanagementsystem.DTO.StudentResponseDto;
import com.backendProject.librarymanagementsystem.DTO.StudentUpdateEmailRequestDto;
import com.backendProject.librarymanagementsystem.Entity.Student;
import com.backendProject.librarymanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto)
    {
        studentService.addStudent(studentRequestDto);
        return "Student Added Successfully";
    }
    @GetMapping("/get_students")
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }
    @GetMapping("/studentsByname")
    public List<Student> getStudentsByname(@RequestParam("name") String name)
    {
       return studentService.getStudentsByname(name);
    }
    //we can create our own findby anything , because we can use only findbyId
    //for others like this below we have to create by own in repository first
    @GetMapping("/find_by_email")
    public String findStudentbyEmail(@RequestParam("mail") String email)
    {
        return studentService.findStudentbyEmail(email);
    }
    @GetMapping("/find_by_age")
    public List<Student> getstudentbyAge(@RequestParam("age") int age)
    {
        return studentService.getstudentbyAge(age);
    }
    @PutMapping("/update_email")
    public StudentResponseDto updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }
}
