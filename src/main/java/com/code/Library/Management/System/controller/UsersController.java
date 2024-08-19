package com.code.Library.Management.System.controller;

import com.code.Library.Management.System.models.BookRequest;
import com.code.Library.Management.System.models.BookResponse;
import com.code.Library.Management.System.models.UserRequest;
import com.code.Library.Management.System.models.UserResponse;
import com.code.Library.Management.System.service.BookService;
import com.code.Library.Management.System.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("api/v1/users")

public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping
    public ResponseEntity<Page<UserResponse>> searchUser(@RequestParam(value = "pageSize",defaultValue = "10") int size,
                                                         @RequestParam (value = "pageNumber",defaultValue ="0")int pageNumber
            , @RequestParam("searchPhrase") String query) {
        Page<UserResponse> user = usersService.findAll(query,size,pageNumber);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(usersService.save(userRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = usersService.update(id, userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id) {
        UserResponse userResponse = usersService.findById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }



}
