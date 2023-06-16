package com.backendProject.librarymanagementsystem.Repository;

import com.backendProject.librarymanagementsystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
