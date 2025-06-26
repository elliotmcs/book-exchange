package com.elliotmcs.book_exchange_api.repository;

import com.elliotmcs.book_exchange_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}