package com.selman.bookstore.web.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.selman.bookstore.domain.Book;
import com.selman.bookstore.dto.AuthorDTO;
import com.selman.bookstore.dto.BookDTO;
import com.selman.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Her testten önce veritabanını temizle
        bookRepository.deleteAll();
    }

    @Test
    void testCreateBook_shouldReturn201Created() throws Exception {

        // Given (Hazırlık)
        long databaseSizeBeforeCreate = bookRepository.count();

        AuthorDTO authorDTO = new AuthorDTO(null, "George", "Orwell");
        BookDTO bookDTO = new BookDTO(null, "1984", "978-0451524935", 1949, Set.of(authorDTO));

        // When & Then (Eylem & Doğrulama)
        mockMvc.perform(post("/api/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value("1984"))
                .andExpect(jsonPath("$.isbn").value("978-0451524935"));

        // Verify database state
        assertThat(bookRepository.count()).isEqualTo(databaseSizeBeforeCreate + 1);

    }
}