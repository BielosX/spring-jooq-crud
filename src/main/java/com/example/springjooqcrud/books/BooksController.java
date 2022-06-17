package com.example.springjooqcrud.books;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BooksController {

    private final BooksRepository booksRepository;

    @GetMapping(path = "books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookView>> getBooks() {
        return ResponseEntity.ok(booksRepository.getAll());
    }

    @GetMapping(path = "books/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookView> getBookById(@PathVariable UUID bookId) {
        return booksRepository.getById(bookId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "books", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<BookView> saveBook(@RequestBody Book book) {
        return ResponseEntity.ok(booksRepository.save(book));
    }
}
