package com.selman.bookstore.repository;

import com.selman.bookstore.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // JpaRepository provides all basic CRUD operations (findAll, findById, save, delete, etc.)
    // We don't need to add any custom methods here for now.
}
