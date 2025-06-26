package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.ResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResponseService {
    List<ResponseDTO> getAllResponses();
    Optional<ResponseDTO> getResponseById(UUID id);
    ResponseDTO saveResponse(ResponseDTO responseDTO);
    ResponseDTO updateResponse(UUID id, ResponseDTO responseDTO);
    void deleteResponse(UUID id);
}