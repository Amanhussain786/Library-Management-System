package com.backendProject.librarymanagementsystem.Service;

import com.backendProject.librarymanagementsystem.DTO.StudentRequestDto;
import com.backendProject.librarymanagementsystem.DTO.StudentResponseDto;
import com.backendProject.librarymanagementsystem.DTO.StudentUpdateAgeRequestDto;
import com.backendProject.librarymanagementsystem.DTO.StudentUpdateEmailRequestDto;
import com.backendProject.librarymanagementsystem.Entity.LibraryCard;
import com.backendProject.librarymanagementsystem.Entity.Student;
import com.backendProject.librarymanagementsystem.Enum.CardStatus;
import com.backendProject.librarymanagementsystem.Enum.Department;
import com.backendProject.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentRequestDto studentRequestDto) {
        //create student object
        Student student = new Student();
        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setEmail(studentRequestDto.getEmail());

        //create card object
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        //set card
        student.setCard(card);

        //check

        studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByname(String name) {
        List<Student> ans = new ArrayList<>();
        List<Student> li = studentRepository.findAll();
        for (int i = 0; i < li.size(); i++) {
            if (li.get(i).getName().equals(name)) {
                ans.add(li.get(i));
            }
        }
        return ans;
//       List<Student> ans = studentRepository.findByName(name);
//       return ans;
    }

    public String findStudentbyEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        return student.getName();
    }

    public List<Student> studentFindByAge(int age) {
        return studentRepository.findByAge(age);
    }

    /* DTO API(Data Transfer Object)
     If a user have your primary key then he can update your data in database so
     to overcome from this we use DTO where we don't show primary key to user by making another class*/
    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto) {
        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());
        //update
        Student update = studentRepository.save(student);
        //convert to StudentResponseDto because return type is StudentResponseDto
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(update.getId());
        studentResponseDto.setEmail(update.getEmail());
        studentResponseDto.setName(update.getName());

        return studentResponseDto;
    }

    public Department findDepartmentByEmail(String email) {
        Student x = studentRepository.findByEmail(email);
        return x.getDepartment();
    }

    public String deleteStudentByName(String name) {
        List<Student> students = studentRepository.findAll();
        boolean studentpresent = false;
        for (int i = 0; i < students.size(); i++) {
            Student obj = students.get(i);
            if (obj.getName().equals(name)) {
                int identity = obj.getId();
                studentRepository.deleteById(identity);
                studentpresent = true;
            }
        }
        if (!studentpresent)
            return "Student Does not Exist";
        else return "Student Removed Successfully";
    }

    public String updateStudentAge(StudentUpdateAgeRequestDto studentUpdateAgeRequestDto) throws Exception {

//        Student student = studentRepository.findById(studentUpdateAgeRequestDto.getId()).orElse(null);
//        if(student==null)
//        {
//            throw new Exception("Student does not exist");
//        }
//        else {
//            student.setAge(studentUpdateAgeRequestDto.getAge());
//
//            studentRepository.save(student);
//            return "Student Age Updated Successfully";
//        }

        Student student;

        try {
            student = studentRepository.findById(studentUpdateAgeRequestDto.getId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Student Doesn't Exist");
        }
            student.setAge(studentUpdateAgeRequestDto.getAge());

            studentRepository.save(student);
            return "Student Age Updated Successfully";

    }

}