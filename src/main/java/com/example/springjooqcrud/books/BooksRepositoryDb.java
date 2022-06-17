package com.example.springjooqcrud.books;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.codegen.tables.Books;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Primary
@RequiredArgsConstructor
public class BooksRepositoryDb implements BooksRepository {
    private final DSLContext context;

    @Override
    public List<BookView> getAll() {
        return context.selectFrom(Books.BOOKS).fetchInto(BookView.class);
    }

    @Override
    public BookView save(Book book) {
        UUID id = UUID.randomUUID();
        context.insertInto(Books.BOOKS, Books.BOOKS.BOOK_ID, Books.BOOKS.AUTHOR, Books.BOOKS.TITLE)
                .values(id, book.getAuthor(), book.getTitle())
                .execute();
        return new BookView(id, book.getTitle(), book.getAuthor());
    }

    @Override
    public Optional<BookView> getById(UUID id) {
        return context.selectFrom(Books.BOOKS).where(Books.BOOKS.BOOK_ID.eq(id)).fetchOptional()
                .map(record -> new BookView(record.getBookId(), record.getTitle(), record.getAuthor()));
    }
}
