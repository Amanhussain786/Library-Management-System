package com.backendProject.librarymanagementsystem.Entity;

import com.backendProject.librarymanagementsystem.Enum.CardStatus;
import com.backendProject.librarymanagementsystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    Department department;
    @Column(unique = true)
    private String email;
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    LibraryCard card;
}
