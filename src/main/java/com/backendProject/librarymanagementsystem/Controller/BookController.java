package com.backendProject.librarymanagementsystem.Controller;

import com.backendProject.librarymanagementsystem.Entity.Book;
import com.backendProject.librarymanagementsystem.Service.AuthorService;
import com.backendProject.librarymanagementsystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public String addBook(@RequestBody Book book) throws Exception {
        try {
            bookService.addBook(book);
        }
       catch (Exception e)
       {
           throw new Exception(e.getMessage()+"Book not Added");
       }
        return "Book Added Successfully";
    }
    @GetMapping("/get_books")
    public List<Book> getBooks()
    {
        return bookService.getBooks();
    }
}
