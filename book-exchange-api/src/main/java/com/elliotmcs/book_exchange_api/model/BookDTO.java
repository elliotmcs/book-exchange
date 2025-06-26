package com.elliotmcs.book_exchange_api.model;

import java.util.UUID;
import java.util.Set;

public record BookDTO(UUID id, String title, String author, String isbn, int quality, UUID userId, Set<UUID> tagIds, boolean isPublic) {}