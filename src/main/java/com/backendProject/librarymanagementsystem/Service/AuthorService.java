package com.backendProject.librarymanagementsystem.Service;

import com.backendProject.librarymanagementsystem.Entity.Author;
import com.backendProject.librarymanagementsystem.Entity.Book;
import com.backendProject.librarymanagementsystem.Repository.AuthorRepository;
import com.backendProject.librarymanagementsystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public void addAuthor(Author author)
    {
        authorRepository.save(author);
    }
    public List<Author> getAuthors()
    {
        return authorRepository.findAll();
    }

}
