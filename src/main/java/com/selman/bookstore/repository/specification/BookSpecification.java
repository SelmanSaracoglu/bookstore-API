package com.selman.bookstore.repository.specification;

import com.selman.bookstore.domain.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecification {
    public Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return criteriaBuilder.conjunction(); // Eğer title boşsa, hiçbir filtre uygulama
            }
            // title alanında, büyük/küçük harf duyarsız bir şekilde arama yap
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        };
    }

    public Specification<Book> hasPublicationYear(Integer year) {
        return (root, query, criteriaBuilder) -> {
            if (year == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("publicationYear"), year);
        };
    }
}
