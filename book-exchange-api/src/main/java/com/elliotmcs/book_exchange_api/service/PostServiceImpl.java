package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.Post;
import com.elliotmcs.book_exchange_api.model.PostDTO;
import com.elliotmcs.book_exchange_api.model.User;
import com.elliotmcs.book_exchange_api.model.Book;
import com.elliotmcs.book_exchange_api.repository.PostRepository;
import com.elliotmcs.book_exchange_api.repository.UserRepository;
import com.elliotmcs.book_exchange_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<PostDTO> getPostById(UUID id) {
        return postRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public PostDTO savePost(PostDTO postDTO) {
        Post post = convertToEntity(postDTO);
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    @Override
    public PostDTO updatePost(UUID id, PostDTO postDTO) {
        UUID userId = postDTO.userId();
        User user = userRepository.findById(userId).orElseThrow();

        Set<Book> books = getBooksFromPostDTO(postDTO);
        
        Post post = postRepository.findById(id).orElseThrow();
        post.setUser(user);
        post.setBooks(books);
        post.setIsOpen(postDTO.isOpen());

        Post updatedPost = postRepository.save(post);
        return convertToDTO(updatedPost);
    }

    @Override
    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }

    // Convert Post Entity to PostDTO
    private PostDTO convertToDTO(Post post) {
        Set<UUID> bookIds = post.getBooks()
            .stream()
            .map(book -> book.getId())
            .collect(Collectors.toSet());
        return new PostDTO(
            post.getId(),
            post.getUser().getId(),
            bookIds,
            post.getIsOpen()
        );
    }

    // Convert PostDTO to Post Entity
    private Post convertToEntity(PostDTO postDTO) {
        UUID userId = postDTO.userId();
        User user = userRepository.findById(userId).orElseThrow();

        Set<Book> books = getBooksFromPostDTO(postDTO);
        
        Post post = new Post();
        post.setUser(user);
        post.setBooks(books);
        post.setIsOpen(postDTO.isOpen());

        return post;
    }

    private Set<Book> getBooksFromPostDTO(PostDTO postDTO) {
        Set<UUID> bookIds = postDTO.bookIds();
        return new HashSet<Book>(bookRepository.findByIdIn(bookIds).orElseThrow());
    }
}