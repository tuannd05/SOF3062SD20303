package com.example.sof3062sd20303.controller;

import com.example.sof3062sd20303.dto.BookRequest;
import com.example.sof3062sd20303.dto.BookResponse;
import com.example.sof3062sd20303.service.BookService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> add(@RequestBody BookRequest bookRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.add(bookRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<BookResponse> update(@RequestBody BookRequest bookRequest, @PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(bookRequest, id));
    }

    @DeleteMapping("{id)")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        bookService.delete(id);

        return ResponseEntity.noContent().build(); // 204
    }
}
