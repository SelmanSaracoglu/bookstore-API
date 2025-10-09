package com.selman.bookstore.web.rest;


import com.selman.bookstore.domain.Book;
import com.selman.bookstore.dto.BookDTO;
import com.selman.bookstore.mapper.BookMapper;
import com.selman.bookstore.repository.BookRepository;
import com.selman.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book createdBook = bookService.createBook(book);
        BookDTO resultDTO = bookMapper.toDTO(createdBook);
        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<BookDTO>> getAllBooks(Pageable pageable) {
        Page<Book> bookPage = bookService.findAllBooks(pageable);
        Page<BookDTO> bookDTOPage = bookPage.map(bookMapper::toDTO);
        return ResponseEntity.ok(bookDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findBookById(id);
        return bookOptional
                .map(book -> ResponseEntity.ok(bookMapper.toDTO(book)))
                .orElse(ResponseEntity.notFound().build());
    }


}
