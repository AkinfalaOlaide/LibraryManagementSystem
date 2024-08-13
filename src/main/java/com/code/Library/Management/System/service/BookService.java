package com.code.Library.Management.System.service;

import com.code.Library.Management.System.models.BookRequest;
import com.code.Library.Management.System.models.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.text.ParseException;
import java.util.Date;
public interface BookService {
    Page<BookResponse> findAll(String query,int pageNumber, int pageSize);

    BookResponse findById(Long theId);
    void deleteById(Long theId);

    BookResponse save(BookRequest book) throws ParseException;

    BookResponse update(Long id, BookRequest book) throws ParseException;
}
