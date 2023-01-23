package com.kumar.restwebservices.restfulwebservices.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="books")
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ISBN")
    private Integer id;
    @NotBlank
    @Column(name = "book_title", length = 100, nullable = false)
    private  String title;
    @NotBlank
    private  String author;
    @NotBlank
    private  String genres;

}
