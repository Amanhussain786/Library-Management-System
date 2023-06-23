package com.backendProject.librarymanagementsystem.Repository;

import com.backendProject.librarymanagementsystem.Entity.Student;
import com.backendProject.librarymanagementsystem.Enum.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

   //findBy{attribute name}
   Student findByEmail(String email);
   List<Student> findByAge(int age);

  // List<Student> findByName(String name);

}
