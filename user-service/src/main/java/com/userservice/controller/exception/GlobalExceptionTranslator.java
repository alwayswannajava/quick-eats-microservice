package com.userservice.controller.exception;

import com.mongodb.MongoException;
import com.userservice.service.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionTranslator {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                      HttpServletRequest request) {
        String correlationId = getCorrelationId(request);

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Validation failed");
        problem.setDetail(ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", ")));
        problem.setType(URI.create("invalid-input"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("correlationId", correlationId);

        log.warn("Validation error, correlationId={}", correlationId);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.valueOf("application/problem+json"))
                .body(problem);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        String correlationId = getCorrelationId(request);

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("User not found");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("user-not-found"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("correlationId", correlationId);

        log.warn("Validation error, correlationId={}", correlationId);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.valueOf("application/problem+json"))
                .body(problem);
    }

    @ExceptionHandler(MongoException.class)
    public ResponseEntity<ProblemDetail> handleMongoException(MongoException ex, HttpServletRequest request) {
        String correlationId = getCorrelationId(request);
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("User processing error");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("user-process-error"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("correlationId", correlationId);

        log.warn("Validation error, correlationId={}", correlationId);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.valueOf("application/problem+json"))
                .body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGeneric(Exception ex, HttpServletRequest request) {
        String correlationId = getCorrelationId(request);

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Internal server error");
        problem.setDetail("Unexpected error occurred");
        problem.setType(URI.create("internal-error"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("correlationId", correlationId);

        log.error("Unhandled exception, correlationId: {}", correlationId);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.valueOf("application/problem+json"))
                .body(problem);
    }

    private String getCorrelationId(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("X-Correlation-ID"))
                .orElse(UUID.randomUUID().toString());
    }
}
