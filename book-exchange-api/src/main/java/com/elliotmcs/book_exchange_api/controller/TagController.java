package com.elliotmcs.book_exchange_api.controller;

import com.elliotmcs.book_exchange_api.model.TagDTO;
import com.elliotmcs.book_exchange_api.service.TagService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDTO> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable UUID id) {
        Optional<TagDTO> tag = tagService.getTagById(id);
        return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TagDTO createTag(@RequestBody TagDTO tagDTO) {
        return tagService.saveTag(tagDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable UUID id, @RequestBody TagDTO tagDTO) {
        try {
            TagDTO updatedTag = tagService.updateTag(id, tagDTO);
            return ResponseEntity.ok(updatedTag);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}