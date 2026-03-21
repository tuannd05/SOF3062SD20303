package com.example.sof3062sd20303.service.TodoImpl;

import com.example.sof3062sd20303.dto.BookRequest;
import com.example.sof3062sd20303.dto.BookResponse;
import com.example.sof3062sd20303.entity.Book;
import com.example.sof3062sd20303.exception.CustomResourceNotFoundException;
import com.example.sof3062sd20303.repository.BookRepository;
import com.example.sof3062sd20303.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

     private final ModelMapper modelMapper;


    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper
                        .map(book, BookResponse.class))
                .toList();
    }

    @Override
    public BookResponse findById(Long id) {
        return bookRepository.findById(id)
                .map(book -> modelMapper
                        .map(book, BookResponse.class))
                .orElseThrow(() -> new CustomResourceNotFoundException("Book not found for this id: " + id));
    }

    @Override
    public BookResponse add(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        book.setIsbn(generalIsbn());
        return modelMapper.map(bookRepository.save(book), BookResponse.class);
    }

    private String generalIsbn() {
        return "ISBN-" + UUID.randomUUID().toString().substring(0,13);
    }

    @Override
    public BookResponse update(BookRequest bookRequest, long id) {
        return bookRepository.findById(id)
                .map(b-> {
                    if (bookRequest.getTitle() != null) b.setTitle(bookRequest.getTitle());
                    if (bookRequest.getAuthor() != null) b.setAuthor(bookRequest.getAuthor());
                    if (bookRequest.getPrice() != 0) b.setPrice(bookRequest.getPrice());

                    bookRepository.save(b);
                    return modelMapper.map(b, BookResponse.class);
                })
                .orElseThrow(() -> new CustomResourceNotFoundException("Book not found for this id: " + id));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
