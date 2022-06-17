package com.example.springjooqcrud.books;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepositoryJdbc extends CrudRepository<BookEntity, UUID> {
    List<BookEntity> findAll();
    Optional<BookEntity> findByBookId(UUID id);

    BookEntity save(BookEntity book);
}
