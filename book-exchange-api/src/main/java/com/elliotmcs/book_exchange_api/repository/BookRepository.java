package com.elliotmcs.book_exchange_api.repository;

import com.elliotmcs.book_exchange_api.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.Collection;
import java.util.Optional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT b FROM Book b WHERE b.id IN :bookIds")
    Optional<Collection<Book>> findByIdIn(@Param("bookIds") Collection<UUID> bookIds);
}