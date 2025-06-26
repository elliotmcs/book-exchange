package com.elliotmcs.book_exchange_api.model;

import java.util.UUID;
import java.util.Set;

public record ResponseDTO(int id, UUID userId, Set<UUID> bookIds, boolean isAccepted) {}