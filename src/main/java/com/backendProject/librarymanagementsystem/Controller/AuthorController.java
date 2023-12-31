package com.backendProject.librarymanagementsystem.Controller;

import com.backendProject.librarymanagementsystem.DTO.AuthorRequestDto;
import com.backendProject.librarymanagementsystem.DTO.AuthorResponseDto;
import com.backendProject.librarymanagementsystem.DTO.BookResponseDto;
import com.backendProject.librarymanagementsystem.Entity.Author;
import com.backendProject.librarymanagementsystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public AuthorResponseDto addAuthor(@RequestBody AuthorRequestDto authorRequestDto)
    {
        return authorService.addAuthor(authorRequestDto);
    }
    @GetMapping("/get_authors")
    public List<Author> getAuthors()
    {
        return authorService.getAuthors();
    }

    @GetMapping("/get_mobile_number_by_id")
    public ResponseEntity getMobileNumberById(@RequestParam("id") int id)
    {
        try {
            String mobNo = authorService.getMobileNumberById(id);
            return new ResponseEntity<>(mobNo,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
