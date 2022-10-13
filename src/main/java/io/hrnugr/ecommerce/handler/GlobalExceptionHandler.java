package io.hrnugr.ecommerce.handler;

import io.hrnugr.ecommerce.handler.exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author harun ugur
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .message(ex.getMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .message(ex.getMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ApiSubError> errors = new ArrayList<>();
        errors.add(new ApiSubError(ex.getParameterName(), ex.getParameterName() + " parameter is missing"));


        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message("Missing Parameters")
                .errors(errors)
                .build();

        return build(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .message(ex.getMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiSubError> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.add(new ApiSubError(error.getField(), error.getDefaultMessage())));


        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .message("Validation failed")
                .errors(errors)
                .build();

        return build(apiError);
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<Object> missingRequestHeaderException(MissingRequestHeaderException ex) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .message("Request header is missing or incorrect")
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(NOT_FOUND)
                .message(ex.getLocalizedMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    @ExceptionHandler(AuthFailException.class)
    public ResponseEntity<Object> handleAuthFailException(AuthFailException ex) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> throwable(Throwable ex) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(INTERNAL_SERVER_ERROR)
                .message("Internal Server Error")
                .errors(new ArrayList<>())
                .build();

        return build(apiError);
    }

    private ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
