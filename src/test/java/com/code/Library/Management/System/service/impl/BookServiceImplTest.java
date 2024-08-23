package com.code.Library.Management.System.service.impl;

import com.code.Library.Management.System.entity.Book;
import com.code.Library.Management.System.exception.IllegalArgumentException;
import com.code.Library.Management.System.exception.ResourceNotFoundException;
import com.code.Library.Management.System.models.BookRequest;
import com.code.Library.Management.System.models.BookResponse;
import com.code.Library.Management.System.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    private BookRepo bookRepo;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        Date date = new Date();
        Book book = new Book(1L,"Power Must Change Hands",date,"Olukoya Michael",40);
        int pageNumber = 1;
        int pageSize = 10;
        String query = "Power";
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        var page = new PageImpl(List.of(book), pageable, 1L);
        when(bookRepo.findAll( Mockito.any(Specification.class),Mockito.any(Pageable.class))).thenReturn(page);
        var response = bookServiceImpl.findAll(query,pageNumber,pageSize);
        assertEquals(1L,response.getContent().size());
    }
    @Test
    void findById() {
        Date date = new Date();
        Long id = 1L;
        Book book = new Book(id,"Power Must Change Hands",date,"Olukoya Michael",40);

        when(bookRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> bookServiceImpl.findById(1L));

        when(bookRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(book));
        var response = bookServiceImpl.findById(id);
        assertEquals(book.getId(),response.getId());
        assertEquals(book.getName(),response.getName());
        assertEquals(book.getDateOfRelease(),response.getDateOfRelease());
        assertEquals(book.getAuthorName(),response.getAuthorName());
        assertEquals(book.getCopies(),response.getCopies());
    }

    @Test
    void deleteById() {
        Date date = new Date();
        Long id = 1L;

        doThrow(new IllegalArgumentException("Book ID does not exist")).when(bookRepo).deleteById(id);
        assertThrows(IllegalArgumentException.class, () -> bookServiceImpl.deleteById(id));

        Book book = new Book(id,"Power Must Change Hands",date,"Olukoya Michael",40);
        when(bookRepo.findById(id)).thenReturn(Optional.of(book));
        doNothing().when(bookRepo).deleteById(id);
        bookServiceImpl.deleteById(id);

        verify(bookRepo,times(1)).deleteById(id);
    }

    @Test
    void save() {
        Date date = new Date();

        BookRequest bookRequest = new BookRequest("Power Must Change Hands", "Olukoya Michael", date, 40);
        Book savedBook = new Book(1L, "Power Must Change Hands", date,"Olukoya Michael", 40);
        when(bookRepo.save(Mockito.any(Book.class))).thenReturn(savedBook);

        BookResponse response = bookServiceImpl.save(bookRequest);

        assertEquals(1L,response.getId());
       assertEquals("Olukoya Michael",response.getAuthorName());
       assertEquals("Power Must Change Hands",response.getName());
       assertEquals(date,response.getDateOfRelease());
       assertEquals(40,response.getCopies());
        verify(bookRepo).save(Mockito.any(Book.class));
    }

    @Test
    void update() {
        Date date = new Date();
        Long id = 1L;

        doThrow(new IllegalArgumentException("Book ID does not exist")).when(bookRepo).deleteById(id);
        assertThrows(IllegalArgumentException.class, () -> bookServiceImpl.deleteById(id));

        BookRequest bookRequest = new BookRequest("Power Must Change Hands", "Olukoya Michael", date, 40);
        Book updatedBook = new Book(1L, "Power Must Change Hands", date,"Olukoya Michael", 40);

        when(bookRepo.findById(id)).thenReturn(Optional.of(updatedBook));
        when(bookRepo.save(any(Book.class))).thenReturn(updatedBook);

        BookResponse response = bookServiceImpl.update(id,bookRequest);

        assertEquals(1L,response.getId());
        assertEquals("Olukoya Michael",response.getAuthorName());
        assertEquals("Power Must Change Hands",response.getName());
        assertEquals(date,response.getDateOfRelease());
        assertEquals(40,response.getCopies());

        verify(bookRepo).save(Mockito.any(Book.class));
    }
}