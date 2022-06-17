package com.example.springjooqcrud.books;

import lombok.Value;

import java.util.UUID;

@Value
public class BookView {
    UUID bookId;
    String title;
    String author;
}
