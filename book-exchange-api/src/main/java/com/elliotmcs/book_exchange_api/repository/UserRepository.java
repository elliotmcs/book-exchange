package com.elliotmcs.book_exchange_api.repository;

import com.elliotmcs.book_exchange_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}