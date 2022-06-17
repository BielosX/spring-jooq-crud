package com.example.springjooqcrud.books;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class BooksRepositoryInMemory implements BooksRepository {

    private final Map<UUID, Book> books = new ConcurrentHashMap<>();

    @Override
    public List<BookView> getAll() {
        return books.entrySet().stream()
                .map(entry -> new BookView(entry.getKey(), entry.getValue().getTitle(), entry.getValue().getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    public BookView save(Book book) {
        UUID id = UUID.randomUUID();
        books.put(id, book);
        return new BookView(id, book.getTitle(), book.getAuthor());
    }

    @Override
    public Optional<BookView> getById(UUID id) {
        return Optional.ofNullable(books.get(id)).map(book -> new BookView(id, book.getTitle(), book.getAuthor()));
    }
}
