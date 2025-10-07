package com.selman.bookstore.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true, nullable = false)
    private String isbn;

    private int publicationYear;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    //Const
    public Book() {    }

    //Getter- Setter

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public int getPublicationYear() {return publicationYear;}
    public void setPublicationYear(int publicationYear) {this.publicationYear = publicationYear;}

    public Set<Author> getAuthors() {return authors;}
    public void setAuthors(Set<Author> authors) {this.authors = authors;}

    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }
}
