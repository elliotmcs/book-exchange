package com.elliotmcs.book_exchange_api.model;

import com.elliotmcs.book_exchange_api.model.Tag;
import com.elliotmcs.book_exchange_api.model.User;

import jakarta.persistence.*;

import java.util.UUID;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String author;
    private String isbn;
    private int quality;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "book_to_tag",
        joinColumns = { @JoinColumn(name = "tagId") },
        inverseJoinColumns = { @JoinColumn(name = "bookId") }
    )
    private Set<Tag> tags = new HashSet<>();
    
    private boolean isPublic;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getISBN() { return isbn; }
    public void setISBN(String isbn) { this.isbn = isbn; }

    public int getQuality() { return quality; }
    public void setQuality(int quality) { this.quality = quality; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }

    public boolean getIsPublic() { return isPublic; }
    public void setIsPublic(boolean isPublic) { this.isPublic = isPublic; }
}