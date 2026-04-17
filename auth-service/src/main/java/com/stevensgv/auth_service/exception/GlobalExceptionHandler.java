package com.stevensgv.auth_service.exception;

import com.stevensgv.auth_service.dto.ApiErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException ex,
                                                                      HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);

    }

    private ResponseEntity<ApiErrorResponseDTO> buildResponse(HttpStatus status,
                                                              String message,
                                                              HttpServletRequest request) {

        ApiErrorResponseDTO response = new ApiErrorResponseDTO(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI());

        return ResponseEntity.status(status).body(response);
    }
}
