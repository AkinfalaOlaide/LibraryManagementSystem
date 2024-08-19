package com.code.Library.Management.System.service;

import com.code.Library.Management.System.enums.Status;
import com.code.Library.Management.System.models.BookRequest;
import com.code.Library.Management.System.models.BookResponse;
import com.code.Library.Management.System.models.UserRequest;
import com.code.Library.Management.System.models.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

import java.text.ParseException;
import java.util.Date;
public interface UsersService {
    Page<UserResponse> findAll(String query, int pageNumber, int pageSize);

    UserResponse findById(Long theId);
    void deleteById(Long theId);

    UserResponse save(UserRequest user);

    UserResponse update(Long id, UserRequest user);
}
