package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.PostDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    List<PostDTO> getAllPosts();
    Optional<PostDTO> getPostById(UUID id);
    PostDTO savePost(PostDTO postDTO);
    PostDTO updatePost(UUID id, PostDTO postDTO);
    void deletePost(UUID id);
}