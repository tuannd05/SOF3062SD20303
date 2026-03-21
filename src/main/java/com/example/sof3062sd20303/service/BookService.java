package com.example.sof3062sd20303.service;

import com.example.sof3062sd20303.dto.BookRequest;
import com.example.sof3062sd20303.dto.BookResponse;
import com.example.sof3062sd20303.entity.Book;
import com.example.sof3062sd20303.entity.Todo;

import java.util.List;

public interface BookService {

    List<BookResponse> findAll();

    BookResponse findById (Long id);

    BookResponse add(BookRequest bookRequest);

    BookResponse update(BookRequest bookRequest, long id);

    void delete(Long id);

}
