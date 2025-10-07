package com.selman.bookstore.repository;

import com.selman.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    /*
     Finds a book by its unique ISBN.
     Spring Data JPA will automatically generate the query for this method based on its name.
     @param isbn The ISBN to search for.
     @return An Optional containing the found book, or an empty Optional if no book is found.
     */

    Optional<Book> findByIsbn(String isbn);
}
