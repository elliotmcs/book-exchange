package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.BookDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    List<BookDTO> getAllBooks();
    Optional<BookDTO> getBookById(UUID id);
    BookDTO saveBook(BookDTO bookDTO);
    BookDTO updateBook(UUID id, BookDTO bookDTO);
    void deleteBook(UUID id);
}