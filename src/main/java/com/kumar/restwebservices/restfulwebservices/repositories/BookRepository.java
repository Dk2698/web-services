package com.kumar.restwebservices.restfulwebservices.repositories;

import com.kumar.restwebservices.restfulwebservices.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository  extends JpaRepository<Book, Integer> {
    List<Book> findByGenresContaining(String genres);

}
