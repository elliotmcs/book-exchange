package com.elliotmcs.book_exchange_api.service;

import com.elliotmcs.book_exchange_api.model.Response;
import com.elliotmcs.book_exchange_api.model.ResponseDTO;
import com.elliotmcs.book_exchange_api.model.User;
import com.elliotmcs.book_exchange_api.model.Book;
import com.elliotmcs.book_exchange_api.repository.ResponseRepository;
import com.elliotmcs.book_exchange_api.repository.UserRepository;
import com.elliotmcs.book_exchange_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ResponseServiceImpl(ResponseRepository responseRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.responseRepository = responseRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<ResponseDTO> getAllResponses() {
        return responseRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<ResponseDTO> getResponseById(UUID id) {
        return responseRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ResponseDTO saveResponse(ResponseDTO responseDTO) {
        Response response = convertToEntity(responseDTO);
        Response savedResponse = responseRepository.save(response);
        return convertToDTO(savedResponse);
    }

    @Override
    public ResponseDTO updateResponse(UUID id, ResponseDTO responseDTO) {
        UUID userId = responseDTO.userId();
        User user = userRepository.findById(userId).orElseThrow();

        Set<Book> books = getBooksFromResponseDTO(responseDTO);
        
        Response response = responseRepository.findById(id).orElseThrow();
        response.setUser(user);
        response.setBooks(books);
        response.setIsAccepted(responseDTO.isAccepted());

        Response updatedResponse = responseRepository.save(response);
        return convertToDTO(updatedResponse);
    }

    @Override
    public void deleteResponse(UUID id) {
        responseRepository.deleteById(id);
    }

    // Convert Response Entity to ResponseDTO
    private ResponseDTO convertToDTO(Response response) {
        Set<UUID> bookIds = response.getBooks()
            .stream()
            .map(book -> book.getId())
            .collect(Collectors.toSet());
        return new ResponseDTO(
            response.getId(),
            response.getUser().getId(),
            bookIds,
            response.getIsAccepted()
        );
    }

    // Convert ResponseDTO to Response Entity
    private Response convertToEntity(ResponseDTO responseDTO) {
        UUID userId = responseDTO.userId();
        User user = userRepository.findById(userId).orElseThrow();

        Set<Book> books = getBooksFromResponseDTO(responseDTO);
        
        Response response = new Response();
        response.setUser(user);
        response.setBooks(books);
        response.setIsAccepted(responseDTO.isAccepted());

        return response;
    }

    private Set<Book> getBooksFromResponseDTO(ResponseDTO responseDTO) {
        Set<UUID> bookIds = responseDTO.bookIds();
        return new HashSet<Book>(bookRepository.findByIdIn(bookIds).orElseThrow());
    }
}