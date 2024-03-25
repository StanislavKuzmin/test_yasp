package com.github.stanislav.kuzmin.yasp.service;

import com.github.stanislav.kuzmin.yasp.model.Book;
import com.github.stanislav.kuzmin.yasp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.stanislav.kuzmin.yasp.repository.BookSpecifications.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private static final int RECORD_LIMIT = 10;

    private final BookRepository repository;

    public List<Book> getTopTenBooks(Integer year, String column, String sort) {
        String field = convertToFieldName(column);
        Specification<Book> specWithoutYear = columnIsNotNull(field).and(columnOrderBy(field, sort));
        if (year == null) {
            log.info("get list of books with not null column={} and sort={}", column, sort);
            return repository.findAll(specWithoutYear, PageRequest.of(0, RECORD_LIMIT)).getContent();
        } else {
            log.info("get list of books with not null column={} and sort={} from year={}", column, sort, year);
            return repository.findAll(specWithoutYear.and(publicationYear(year)), PageRequest.of(0, RECORD_LIMIT)).getContent();
        }
    }

    private String convertToFieldName(String column) {
        return switch (column) {
            case "title", "authors", "publicationDate", "ratingScore" -> column;
            case "numPages" -> "numberOfPages";
            case "numRatings" -> "numberOfRatings";
            default -> "id";
        };
    }
}
