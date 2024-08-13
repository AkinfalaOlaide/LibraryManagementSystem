package com.code.Library.Management.System.controller;

import com.code.Library.Management.System.models.BookRequest;
import com.code.Library.Management.System.models.BookResponse;
import com.code.Library.Management.System.service.BookService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping
    public ResponseEntity<Page<BookResponse>> searchBooks(@RequestParam("pageSize") int size,
                                                          @RequestParam ("pageNumber")int pageNumber
                                         , @RequestParam("searchPhrase") String query) {
        Page<BookResponse> book = bookService.findAll(query,size,pageNumber);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest) throws ParseException {
        return new ResponseEntity<>(bookService.save(bookRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) throws ParseException {
        BookResponse bookResponse = bookService.update(id, bookRequest);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable Long id) {
        BookResponse book = bookService.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
