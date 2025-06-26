package com.elliotmcs.book_exchange_api.repository;

import com.elliotmcs.book_exchange_api.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}