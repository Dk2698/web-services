package com.kumar.restwebservices.restfulwebservices.controllers;

import com.kumar.restwebservices.restfulwebservices.entities.Book;
import com.kumar.restwebservices.restfulwebservices.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("book/")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book){
        Book book1 = this.bookService.createBook(book);
        return  new ResponseEntity<>(book1, HttpStatus.CREATED);
    }

    @PutMapping("book/{bookId}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book, @PathVariable("bookId") Integer bookId){
        Book book1 = this.bookService.updateBook(book, bookId);
        return  ResponseEntity.ok(book1);
    }

    @GetMapping("book/")
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.ok(this.bookService.getAllBook());
    }

    @GetMapping("book/{bookId}")
    public ResponseEntity<Book> singleBook(@PathVariable Integer bookId){
        return ResponseEntity.ok(this.bookService.getBySingleBook(bookId));
    }

    @GetMapping("book/genres")
    public ResponseEntity<List<String>> getAllGenres(){
        return ResponseEntity.ok(this.bookService.getAllGenres());
    }

    @GetMapping("book/genres/{genre}")
    public ResponseEntity<List<Book>> getAllBookByGenres(@PathVariable String genre){

        return ResponseEntity.ok(this.bookService.getAllBookByGenres(genre));
    }
}
