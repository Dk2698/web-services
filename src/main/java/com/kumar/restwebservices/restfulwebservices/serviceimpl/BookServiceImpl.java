package com.kumar.restwebservices.restfulwebservices.serviceimpl;

import com.kumar.restwebservices.restfulwebservices.entities.Book;
import com.kumar.restwebservices.restfulwebservices.repositories.BookRepository;
import com.kumar.restwebservices.restfulwebservices.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl  implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        Book book1 = this.bookRepository.save(book);
        return book1;
    }

    @Override
    public Book updateBook(Book book, Integer bookId) {
        Book book1 = this.bookRepository.findById(bookId).orElseThrow();
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setGenres(book.getGenres());
        // update database
        Book book2 = this.bookRepository.save(book1);
        return book2;
    }

    @Override
    public List<Book> getAllBook() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBySingleBook(Integer bookId) {
        Book book1 = this.bookRepository.findById(bookId).orElseThrow();
        return  book1;
    }

    @Override
    public List<String> getAllGenres() {
        List<Book> books = this.bookRepository.findAll();
        List<String>  genres = books.stream()
                .map(e -> e.getGenres())
                .distinct()
                .collect(Collectors.toList());

        return genres;
    }

    @Override
    public List<Book> getAllBookByGenres(String genre) {
        List<Book> books = this.bookRepository.findByGenresContaining(genre);
        if (books.isEmpty()){
            return  new ArrayList<>();
        }
        return books;
    }
}
