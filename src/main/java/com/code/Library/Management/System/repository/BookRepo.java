package com.code.Library.Management.System.repository;

import com.code.Library.Management.System.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepo  extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {


}

