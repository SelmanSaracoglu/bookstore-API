package com.selman.bookstore.service;


import com.selman.bookstore.domain.Book;
import com.selman.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenCreateBookWithExistingIsbn_thenThrowException() {
        // Given (Hazırlık)
        Book existingBook = new Book();
        existingBook.setIsbn("12345");
        when(bookRepository.findByIsbn("12345")).thenReturn(Optional.of(existingBook));

        Book newBook = new Book();
        newBook.setIsbn("12345");

        // When & Then (Eylem & Doğrulama)
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            bookService.createBook(newBook);
        });

        assertEquals("A book with ISBN 12345 already exists.", exception.getMessage());
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void whenCreateBookWithNewIsbn_thenSaveAndReturnBook(){
        // Given (Hazırlık)
        when(bookRepository.findByIsbn("54321")).thenReturn(Optional.empty());

        Book newBook = new Book();
        newBook.setIsbn(("54321"));
        newBook.setTitle("A New Book");

        // Simüle edilmiş save metodunun, kendisine verilen Book nesnesini geri dönmesini sağlıyoruz.
        when(bookRepository.save(any(Book.class))).thenReturn(newBook);

        // When (Eylem)
        Book savedBook = bookService.createBook(newBook);

        // Then (Doğrulama)
        assertNotNull(savedBook);
        assertEquals("A New Book", savedBook.getTitle());
        verify(bookRepository, times(1)).findByIsbn("54321");
        verify(bookRepository, times(1)).save(newBook);

    }
}
