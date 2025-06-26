package com.elliotmcs.book_exchange_api.controller;

import com.elliotmcs.book_exchange_api.model.ResponseDTO;
import com.elliotmcs.book_exchange_api.service.ResponseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    private final ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping
    public List<ResponseDTO> getAllResponses() {
        return responseService.getAllResponses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getResponseById(@PathVariable UUID id) {
        Optional<ResponseDTO> response = responseService.getResponseById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseDTO createResponse(@RequestBody ResponseDTO responseDTO) {
        return responseService.saveResponse(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateResponse(@PathVariable UUID id, @RequestBody ResponseDTO responseDTO) {
        try {
            ResponseDTO updatedResponse = responseService.updateResponse(id, responseDTO);
            return ResponseEntity.ok(updatedResponse);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable UUID id) {
        responseService.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}