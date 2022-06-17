package com.example.springjooqcrud.books;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "books")
@Value
public class BookEntity implements Persistable<UUID> {
    @Id
    UUID bookId;

    String title;
    String author;

    @Override
    public UUID getId() {
        return bookId;
    }

    @Override
    public boolean isNew() {
        return this.bookId != null;
    }
}
