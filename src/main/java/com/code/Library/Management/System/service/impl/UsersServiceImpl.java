package com.code.Library.Management.System.service.impl;

import com.code.Library.Management.System.entity.Books;
import com.code.Library.Management.System.entity.Users;
import com.code.Library.Management.System.exception.ResourceNotFoundException;
import com.code.Library.Management.System.models.BookRequest;
import com.code.Library.Management.System.models.BookResponse;
import com.code.Library.Management.System.models.UserRequest;
import com.code.Library.Management.System.models.UserResponse;
import com.code.Library.Management.System.repository.UserRepository;
import com.code.Library.Management.System.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserResponse> findAll(String query, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Users> searchResult = userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailAddressContainingIgnoreCaseOrPhoneNumberContainingIgnoreCase
                (query,query,query,query,pageable);
        List<UserResponse> userResponseList = searchResult.getContent().stream()
                .map(users -> new UserResponse(users.getId(),users.getFirstName(),
                        users.getFirstName(), users.getDateOfBirth(),users.getStatus()
                ,users.getEmailAddress(),users.getPhoneNumber()))
                .toList();
        return new PageImpl<>(userResponseList,pageable, searchResult.getTotalElements());
    }

    @Override
    public UserResponse findById(Long theId) {
        var user = userRepository.findById(theId).orElseThrow(()->
                new ResourceNotFoundException("user not found with id " + theId));


        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .status(user.getStatus())
                .emailAddress(user.getEmailAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public void deleteById(Long theId) {
        userRepository.deleteById(theId);

    }

    @Override
    public UserResponse save(UserRequest userRequest) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Users users = Users.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .dateOfBirth(formatDate.parse(userRequest.getDateOfBirth()))
                .status(userRequest.getStatus())
                .emailAddress(userRequest.getEmailAddress())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();

        var saveUser = userRepository.save(users);
        return UserResponse.builder()
                .id(saveUser.getId())
                .firstName(saveUser.getFirstName())
                .lastName(saveUser.getLastName())
                .dateOfBirth(saveUser.getDateOfBirth())
                .status(saveUser.getStatus())
                .emailAddress(saveUser.getEmailAddress())
                .phoneNumber(saveUser.getPhoneNumber())
                .build();
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) throws ParseException {
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with the id "+ id));
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        users.setFirstName(userRequest.getFirstName());
        users.setLastName(userRequest.getLastName());
        users.setDateOfBirth(formatDate.parse(userRequest.getDateOfBirth()));
        users.setStatus(userRequest.getStatus());
        users.setEmailAddress(userRequest.getEmailAddress());
        users.setPhoneNumber(userRequest.getPhoneNumber());

        var saveUsers = userRepository.save(users);
        return UserResponse.builder()
                .id(saveUsers.getId())
                .firstName(saveUsers.getFirstName())
                .lastName(saveUsers.getLastName())
                .dateOfBirth(saveUsers.getDateOfBirth())
                .status(saveUsers.getStatus())
                .emailAddress(saveUsers.getEmailAddress())
                .phoneNumber(saveUsers.getPhoneNumber())
                .build();
    }
}
