package com.elliotmcs.book_exchange_api.model;

import com.elliotmcs.book_exchange_api.model.User;
import com.elliotmcs.book_exchange_api.model.Book;

import jakarta.persistence.*;

import java.util.UUID;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(
        name = "response_to_book",
        joinColumns = { @JoinColumn(name = "bookId") },
        inverseJoinColumns = { @JoinColumn(name = "responseId") }
    )
    private Set<Book> books = new HashSet<>();
    
    private boolean isAccepted;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Set<Book> getBooks() { return books; }
    public void setBooks(Set<Book> books) { this.books = books; }
    
    public boolean getIsAccepted() { return isAccepted; }
    public void setIsAccepted(boolean isAccepted) { this.isAccepted = isAccepted; }
}