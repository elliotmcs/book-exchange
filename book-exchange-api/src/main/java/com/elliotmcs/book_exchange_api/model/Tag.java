package com.elliotmcs.book_exchange_api.model;

import jakarta.persistence.*;

import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String label;

    @ManyToMany(mappedBy = "tags")
    private Set<Book> books = new HashSet<>();

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Set<Book> getBooks() { return books; }
    public void setBooks(Set<Book> books) { this.books = books; }
}