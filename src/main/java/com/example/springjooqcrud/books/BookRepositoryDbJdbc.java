package com.example.springjooqcrud.books;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
@RequiredArgsConstructor
public class BookRepositoryDbJdbc implements BooksRepository {

    private final BookRepositoryJdbc bookRepositoryJdbc;

    @Override
    public List<BookView> getAll() {
        return bookRepositoryJdbc.findAll().stream()
                .map(entity -> new BookView(entity.getBookId(), entity.getTitle(), entity.getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    public BookView save(Book book) {
        UUID id = UUID.randomUUID();
        BookEntity entity = bookRepositoryJdbc.save(new BookEntity(id, book.getTitle(), book.getAuthor()));
        return new BookView(id, entity.getTitle(), entity.getAuthor());
    }

    @Override
    public Optional<BookView> getById(UUID id) {
        return bookRepositoryJdbc.findByBookId(id).map(entity -> new BookView(entity.getBookId(), entity.getTitle(), entity.getAuthor()));
    }
}
