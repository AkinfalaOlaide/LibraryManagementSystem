package com.code.Library.Management.System.repository;

import com.code.Library.Management.System.entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface BookRepo  extends JpaRepository<Books, Long> {

    Page<Books> findByNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(
            String nameQuery, String authorQuery, Pageable pageable);

}

