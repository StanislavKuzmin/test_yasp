package com.github.stanislav.kuzmin.yasp.repository;

import com.github.stanislav.kuzmin.yasp.model.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> columnIsNotNull(String column) {
        return (Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.isNotNull(root.get(column));
    }

    public static Specification<Book> columnOrderBy(String column, String order) {
        return (Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (order.equals("ASC")) {
                query.orderBy(cb.asc(root.get(column)));
            } else if (order.equals("DESC")) {
                query.orderBy(cb.desc(root.get(column)));
            }
            return null;
        };
    }

    public static Specification<Book> publicationYear(int year) {
        return (Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.equal(cb.function("YEAR", Integer.class, root.get("publicationDate")), year);
    }
}
