package com.backendProject.librarymanagementsystem.Service;

import com.backendProject.librarymanagementsystem.DTO.AuthorRequestDto;
import com.backendProject.librarymanagementsystem.DTO.AuthorResponseDto;
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
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto)
    {
        //setting author(Request)
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        author.setAge(authorRequestDto.getAge());
        author.setEmail(authorRequestDto.getEmail());
        author.setMobNo(authorRequestDto.getMobNo());

        authorRepository.save(author);

        //generating response
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setName(author.getName());

        return authorResponseDto;
    }
    public List<Author> getAuthors()
    {
        return authorRepository.findAll();
    }

    public String getMobileNumberById(int id) throws Exception {
        Author author;
        try {
            author = authorRepository.findById(id).get();
            return author.getMobNo();
        }
       catch (Exception e)
       {
           throw new Exception("Author Does not Exist");
       }
    }
}
