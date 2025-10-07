package com.selman.bookstore.mapper;

import com.selman.bookstore.domain.Author;
import com.selman.bookstore.dto.AuthorDTO;

import java.lang.classfile.attribute.AnnotationDefaultAttribute;

public class AuthorMapper {

    public static AuthorDTO toDTO(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorDTO(
                author.getId(),
                author.getFirstName(),
                author.getFirstName()
        );
    }

    public static Author toEntity(AuthorDTO authorDTO) {
        if (authorDTO == null){
            return null;
        }

        Author author = new Author();
        author.setId(authorDTO.id());
        author.setFirstName(authorDTO.firstName());
        author.setLastName(authorDTO.lastName());
        return author;
    }


}
