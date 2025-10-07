package com.selman.bookstore.mapper;

import com.selman.bookstore.domain.Book;
import com.selman.bookstore.dto.BookDTO;

import java.util.stream.Collectors;

public class BookMapper {

    public BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                // Author set'ini AuthorDTO set'ine dönüştür
                book.getAuthors().stream()
                        .map(AuthorMapper::toDTO)
                        .collect(Collectors.toSet())
        );
    }

    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }
        Book book = new Book();
        book.setId(bookDTO.id());
        book.setTitle(bookDTO.title());
        book.setIsbn(bookDTO.isbn());
        book.setPublicationYear(bookDTO.publicationYear());
        // AuthorDTO set'ini Author set'ine dönüştür
        book.setAuthors(bookDTO.authors().stream()
                .map(AuthorMapper::toEntity)
                .collect(Collectors.toSet())
        );
        return book;
    }
}
