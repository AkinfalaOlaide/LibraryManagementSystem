package com.code.Library.Management.System.service.impl;

import com.code.Library.Management.System.entity.Book;
import com.code.Library.Management.System.exception.ResourceNotFoundException;
import com.code.Library.Management.System.models.BookRequest;
import com.code.Library.Management.System.models.BookResponse;
import com.code.Library.Management.System.repository.BookRepo;
import com.code.Library.Management.System.repository.BookSpecificationSearch;
import com.code.Library.Management.System.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    @Override
    public Page<BookResponse> findAll(String query, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Book> searchResult = bookRepo.findAll(BookSpecificationSearch.bookSearchQuery(query),pageable);
        List<BookResponse> bookResponses = searchResult.getContent().stream()
                .map(book -> new BookResponse(book.getId(), book.getName(), book.getAuthorName(), book.getDateOfRelease(),book.getCopies()))
                .toList();
        return new PageImpl<>(bookResponses,pageable,searchResult.getTotalElements());
      }

    @Override
    public BookResponse findById(Long theId) {

        var book = bookRepo.findById(theId).orElseThrow(()->
                new ResourceNotFoundException("book not found with id " + theId));

        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .authorName(book.getAuthorName())
                .dateOfRelease(book.getDateOfRelease())
                .copies(book.getCopies())
                .build();

    }

    @Override
    public void deleteById(Long theId) {
        bookRepo.deleteById(theId);

    }

    @Override
    public BookResponse save(BookRequest bookRequest){
        Book book = Book.builder()
                .name(bookRequest.getName())
                .authorName(bookRequest.getAuthorName())
                .dateOfRelease(bookRequest.getDateOfRelease())
                .copies(bookRequest.getCopies())
                .build();
        var saveBook = bookRepo.save(book);
        return BookResponse.builder()
                .id(saveBook.getId())
                .name(saveBook.getName())
                .authorName(saveBook.getAuthorName())
                .dateOfRelease(saveBook.getDateOfRelease())
                .copies(saveBook.getCopies())
                .build();
    }

    @Override
    public BookResponse update(Long id, BookRequest bookRequest) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("book not found with the id "+ id));

        book.setName(bookRequest.getName());
        book.setAuthorName(bookRequest.getAuthorName());
        book.setDateOfRelease(bookRequest.getDateOfRelease());
        book.setCopies(bookRequest.getCopies());

        var saveBooks = bookRepo.save(book);
        return BookResponse.builder()
                .id(saveBooks.getId())
                .name(saveBooks.getName())
                .authorName(saveBooks.getAuthorName())
                .dateOfRelease(saveBooks.getDateOfRelease())
                .copies(saveBooks.getCopies())
                .build();
    }
}
