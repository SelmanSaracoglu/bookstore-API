package com.selman.bookstore.service;


import com.selman.bookstore.domain.Book;
import com.selman.bookstore.repository.BookRepository;
import com.selman.bookstore.repository.specification.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookSpecification bookSpecification;

    // Constructor Injection

    public BookService(BookRepository bookRepository, BookSpecification bookSpecification) {
        this.bookRepository = bookRepository;
        this.bookSpecification = bookSpecification;
    }

    public Page<Book> findAllBooks(String title, Integer publicationYear, Pageable pageable) {
        Specification<Book> spec = Specification
                .where(bookSpecification.hasTitle(title))
                .and(bookSpecification.hasPublicationYear(publicationYear));
        return bookRepository.findAll(spec, pageable);
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        // Check if a book with the same ISBN already exists
        Optional<Book> bookOptional = bookRepository.findByIsbn(book.getIsbn());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("A book with ISBN " + book.getIsbn() + " already exists.");
        }
        return bookRepository.save(book);
    }
}
