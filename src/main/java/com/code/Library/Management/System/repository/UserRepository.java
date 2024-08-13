package com.code.Library.Management.System.repository;

import com.code.Library.Management.System.entity.Books;
import com.code.Library.Management.System.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.hibernate.grammars.hql.HqlParser.LIKE;

public interface UserRepository  extends JpaRepository<Users, Long> {

    Page<Users> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailAddressContainingIgnoreCaseOrPhoneNumberContainingIgnoreCase
            ( String firstnameQuery, String lastnameQuery, String emailQuery, String phoneNumberQuery, Pageable pageable);

}