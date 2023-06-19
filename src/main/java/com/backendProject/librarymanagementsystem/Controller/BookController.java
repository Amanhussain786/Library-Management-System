package com.backendProject.librarymanagementsystem.Controller;

import com.backendProject.librarymanagementsystem.DTO.BookRequestDto;
import com.backendProject.librarymanagementsystem.DTO.BookResponseDto;
import com.backendProject.librarymanagementsystem.Entity.Book;
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
    public BookResponseDto addBook(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        return bookService.addBook(bookRequestDto);
    }
    @GetMapping("/get_books")
    public List<Book> getBooks()
    {
        return bookService.getBooks();
    }
}
