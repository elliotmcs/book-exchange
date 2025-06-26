package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.TagDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagService {
    List<TagDTO> getAllTags();
    Optional<TagDTO> getTagById(UUID id);
    TagDTO saveTag(TagDTO tagDTO);
    TagDTO updateTag(UUID id, TagDTO tagDTO);
    void deleteTag(UUID id);
}