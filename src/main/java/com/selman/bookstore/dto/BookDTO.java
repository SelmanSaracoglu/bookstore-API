package com.selman.bookstore.dto;

import java.util.Set;

public record BookDTO(Long id,
                      String title,
                      String isbn,
                      int publicationYear,
                      Set<AuthorDTO> authors
) {
}
