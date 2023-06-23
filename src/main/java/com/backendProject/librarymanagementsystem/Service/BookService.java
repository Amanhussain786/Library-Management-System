package com.backendProject.librarymanagementsystem.Service;

import com.backendProject.librarymanagementsystem.DTO.BookRequestDto;
import com.backendProject.librarymanagementsystem.DTO.BookResponseDto;
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
    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws Exception {
        //get the author object
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setGenre(bookRequestDto.getGenre());
        book.setPrice(bookRequestDto.getPrice());
        book.setIssued(false);
        book.setAuthor(author);

        author.getBooks().add(book);
        authorRepository.save(author); //will save both book and the author

        //create a response also
       BookResponseDto bookResponseDto = new BookResponseDto();
        bookRequestDto.setTitle(book.getTitle());
        bookRequestDto.setPrice(book.getPrice());

        return bookResponseDto;
    }
    public List<Book> getBooks()
    {
        return bookRepository.findAll();
    }

    public int getBookPrice(String title)
    {
        Book obj = bookRepository.getByTitle(title);
        return obj.getPrice();
    }
    public List<Book> getBooksByPrice(int price)
    {
        List<Book> ans = bookRepository.getByPrice(price);
        return ans;
    }
}
