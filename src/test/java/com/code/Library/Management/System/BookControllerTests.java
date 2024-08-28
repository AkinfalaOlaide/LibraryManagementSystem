package com.code.Library.Management.System;

import com.code.Library.Management.System.controller.BookController;
import com.code.Library.Management.System.models.BookRequest;
import com.code.Library.Management.System.models.BookResponse;
import com.code.Library.Management.System.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookControllerTests {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testSaveBook(){
//        BookRequest bookRequest = new BookRequest("Power must Change Hands","Olukoya Micheal",2024-05-25,
//                20);
//        BookResponse bookResponse = new BookResponse(1L,"Power must Change Hands ","Olukoya Micheal",2024-05-25,
//                20);
//        when(bookService.save(any(BookRequest.class))).thenReturn(bookResponse);

    }
}
