package com.backendProject.librarymanagementsystem.Service;

import com.backendProject.librarymanagementsystem.DTO.StudentResponseDto;
import com.backendProject.librarymanagementsystem.DTO.StudentUpdateEmailRequestDto;
import com.backendProject.librarymanagementsystem.Entity.LibraryCard;
import com.backendProject.librarymanagementsystem.Entity.Student;
import com.backendProject.librarymanagementsystem.Enum.CardStatus;
import com.backendProject.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void addStudent(Student student)
    {
        //set the value of the card
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setValidTill("03/2025");
        card.setStudent(student);

        //set the card attribute in student
        student.setCard(card);

        studentRepository.save(student);
    }
    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }
    public List<Student> getStudentsByname(String name)
    {
        List<Student> ans = new ArrayList<>();
        List<Student> li = studentRepository.findAll();
        for(int i=0;i<li.size();i++)
        {
            if(li.get(i).getName().equals(name))
            {
                ans.add(li.get(i));
            }
        }
        return ans;
    }
    public String findStudentbyEmail(String email)
    {
        Student student = studentRepository.findByEmail(email);
        return student.getName();
    }
    public List<Student> getstudentbyAge(int age)
    {
        List<Student> ans = studentRepository.findByAge(age);
        return ans;
    }
   /* DTO API(Data Transfer Object)
    If a user have your primary key then he can update your data in database so
    to overcome from this we use DTO where we don't show primary key to user by making another class*/
    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());
        //update
        Student update =  studentRepository.save(student);
        //convert to StudentResponseDto becouse return type is StudentResponseDto
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(update.getId());
        studentResponseDto.setEmail(update.getEmail());
        studentResponseDto.setName(update.getName());

        return studentResponseDto;
    }
}
