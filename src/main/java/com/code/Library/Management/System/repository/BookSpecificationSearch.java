package com.code.Library.Management.System.repository;

import com.code.Library.Management.System.entity.Book;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class BookSpecificationSearch {
    public static Specification<Book> bookSearchQuery(String query){
        if (query==null) {
             return Specification.where(null);
        }
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+ query.toLowerCase() +"%" ));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("authorName")),"%"+ query.toLowerCase() +"%" ));
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
