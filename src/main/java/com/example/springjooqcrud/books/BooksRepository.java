package com.example.springjooqcrud.books;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BooksRepository {
    List<BookView> getAll();
    BookView save(Book book);

    Optional<BookView> getById(UUID id);
}