package com.example.sof3062sd20303.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
// bắt lỗi chung
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomErrorDetails> handleAllException(Exception ex, WebRequest request){

        CustomErrorDetails errorDetails = new CustomErrorDetails(LocalDateTime.now(),
                "Validate false", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
// bắt lỗi tài nguyên ví dụ lấy id sai
    @ExceptionHandler(CustomResourceNotFoundException.class)
    public final ResponseEntity<CustomErrorDetails> handleResourceNotFoundException(Exception ex, WebRequest request) {

        CustomErrorDetails errorDetails = new CustomErrorDetails(LocalDateTime.now(),
                "id không tồn tại", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
// bắt lỗi phân quyền
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<CustomErrorDetails> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        CustomErrorDetails errorDetails = new CustomErrorDetails(LocalDateTime.now(),
                "Access Denied: You can not have", request.getDescription(false));
                return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatusCode status,
                                                               WebRequest request) {
        String messages = ex.getFieldErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        CustomErrorDetails errorDetails = new CustomErrorDetails(
                LocalDateTime.now(),
                messages,
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}

