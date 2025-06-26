package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.Tag;
import com.elliotmcs.book_exchange_api.model.TagDTO;
import com.elliotmcs.book_exchange_api.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<TagDTO> getTagById(UUID id) {
        return tagRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public TagDTO saveTag(TagDTO tagDTO) {
        Tag tag = convertToEntity(tagDTO);
        Tag savedTag = tagRepository.save(tag);
        return convertToDTO(savedTag);
    }

    @Override
    public TagDTO updateTag(UUID id, TagDTO tagDTO) {
        Tag tag = tagRepository.findById(id).orElseThrow();
        tag.setLabel(tagDTO.label());
        Tag updatedTag = tagRepository.save(tag);
        return convertToDTO(updatedTag);
    }

    @Override
    public void deleteTag(UUID id) {
        tagRepository.deleteById(id);
    }

    // Convert Tag Entity to TagDTO
    private TagDTO convertToDTO(Tag tag) {
        return new TagDTO(tag.getId(), tag.getLabel());
    }

    // Convert TagDTO to Tag Entity
    private Tag convertToEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setLabel(tagDTO.label());
        return tag;
    }
}