package com.backendProject.librarymanagementsystem.Repository;

import com.backendProject.librarymanagementsystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Book getByTitle(String title);

    List<Book> getByPrice(int price);
}
