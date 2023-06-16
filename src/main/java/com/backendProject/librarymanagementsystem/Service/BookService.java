package com.backendProject.librarymanagementsystem.Service;

import com.backendProject.librarymanagementsystem.Entity.Author;
import com.backendProject.librarymanagementsystem.Entity.Book;
import com.backendProject.librarymanagementsystem.Repository.AuthorRepository;
import com.backendProject.librarymanagementsystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book) throws Exception {
        Author author;
        try {
            author = authorRepository.findById(book.getAuthor().getId()).get();
        }
        catch (Exception e)
        {
            return "Book Not Added";
        }
        List<Book> booksWritten = author.getBooks();
        booksWritten.add(book);

        authorRepository.save(author);
        return "Book Added Successfully";
    }
    public List<Book> getBooks()
    {
        return bookRepository.findAll();
    }
}
