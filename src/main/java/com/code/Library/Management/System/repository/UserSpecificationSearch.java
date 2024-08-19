package com.code.Library.Management.System.repository;

import com.code.Library.Management.System.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecificationSearch {
    public static Specification<User> userSearchQuery(String query){
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),"%"+ query.toLowerCase() +"%" ));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),"%"+ query.toLowerCase() +"%" ));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("status").as(String.class)), "%" + query.toLowerCase() + "%"));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("emailAddress")),"%"+ query.toLowerCase() +"%" ));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("phoneNumber")),"%"+ query.toLowerCase() +"%" ));

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
