package com.code.Library.Management.System.repository;

import com.code.Library.Management.System.entity.Book;
import com.code.Library.Management.System.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository  extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

}