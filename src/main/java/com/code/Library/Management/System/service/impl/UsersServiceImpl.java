package com.code.Library.Management.System.service.impl;

import com.code.Library.Management.System.entity.Book;
import com.code.Library.Management.System.entity.User;
import com.code.Library.Management.System.exception.ResourceNotFoundException;
import com.code.Library.Management.System.models.BookResponse;
import com.code.Library.Management.System.models.UserRequest;
import com.code.Library.Management.System.models.UserResponse;
import com.code.Library.Management.System.repository.BookSpecificationSearch;
import com.code.Library.Management.System.repository.UserRepository;
import com.code.Library.Management.System.repository.UserSpecificationSearch;
import com.code.Library.Management.System.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Page<User> searchResult = userRepository.findAll(UserSpecificationSearch.userSearchQuery(query),pageable);
        List<UserResponse> userResponses = searchResult.getContent().stream()
                .map(user -> new UserResponse(user.getId(), user.getFirstName(), user.getLastName(),
                        user.getDateOfBirth(),user.getStatus(),user.getEmailAddress(),user.getPhoneNumber()))
                .toList();
        return new PageImpl<>(userResponses,pageable,searchResult.getTotalElements());
    }

    @Override
    public UserResponse findById(Long theId) {
        var user = userRepository.findById(theId).orElseThrow(()->
                new ResourceNotFoundException("user not found with id " + theId));


        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
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
    public UserResponse save(UserRequest userRequest){
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .dateOfBirth(userRequest.getDateOfBirth())
                .status(userRequest.getStatus())
                .emailAddress(userRequest.getEmailAddress())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();

        var saveUser = userRepository.save(user);
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
    public UserResponse update(Long id, UserRequest userRequest){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with the id "+ id));

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setStatus(userRequest.getStatus());
        user.setEmailAddress(userRequest.getEmailAddress());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        var saveUsers = userRepository.save(user);
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
