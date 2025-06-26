package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.Book;
import com.elliotmcs.book_exchange_api.model.BookDTO;
import com.elliotmcs.book_exchange_api.model.User;
import com.elliotmcs.book_exchange_api.model.Tag;
import com.elliotmcs.book_exchange_api.repository.BookRepository;
import com.elliotmcs.book_exchange_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(UUID id) {
        return bookRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(UUID id, BookDTO bookDTO) {
        UUID userId = bookDTO.userId();
        User user = userRepository.findById(userId).orElseThrow();
        
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setISBN(bookDTO.isbn());
        book.setQuality(bookDTO.quality());
        book.setUser(user);
        // book.setTags(bookDTO.tags());
        book.setIsPublic(bookDTO.isPublic());
        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

    // Convert Book Entity to BookDTO
    private BookDTO convertToDTO(Book book) {
        Set<UUID> tagIds = book.getTags().stream().map(tag -> {
            return tag.getId();
        }).collect(Collectors.toSet());
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getISBN(), book.getQuality(), book.getUser().getId(), tagIds, book.getIsPublic());
    }

    // Convert BookDTO to Book Entity
    private Book convertToEntity(BookDTO bookDTO) {
        UUID userId = bookDTO.userId();
        User user = userRepository.findById(userId).orElseThrow();

        Book book = new Book();
        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setISBN(bookDTO.isbn());
        book.setQuality(bookDTO.quality());
        book.setUser(user);
        // book.setTags(bookDTO.tags());
        book.setIsPublic(bookDTO.isPublic());
        return book;
    }
}