package com.elliotmcs.book_exchange_api.repository;

import com.elliotmcs.book_exchange_api.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResponseRepository extends JpaRepository<Response, UUID> {
}