package com.elliotmcs.book_exchange_api.model;

import java.util.UUID;

public record UserDTO(UUID id, String username, String email, int rating, boolean isActive) {}