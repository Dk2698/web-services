package com.kumar.restwebservices.restfulwebservices.services;

import com.kumar.restwebservices.restfulwebservices.entities.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);

    Book updateBook(Book book ,Integer bookId);

    List<Book> getAllBook();

    Book getBySingleBook(Integer bookId);

    List<String> getAllGenres();

    List<Book> getAllBookByGenres(String genre);

}
