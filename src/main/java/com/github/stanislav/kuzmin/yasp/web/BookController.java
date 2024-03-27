package com.github.stanislav.kuzmin.yasp.web;

import com.github.stanislav.kuzmin.yasp.model.Book;
import com.github.stanislav.kuzmin.yasp.service.BookService;
import com.github.stanislav.kuzmin.yasp.util.validation.StringEnumeration;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = BookController.REST_URL)
@Validated
@RequiredArgsConstructor
public class BookController {

    public static final String REST_URL = "/api/top10";

    private final BookService service;

    @GetMapping
    public List<Book> getTopTenBooks(@Range(min = 1980, max = 2023) @RequestParam(name = "year", required = false) Integer year,
                                    @RequestParam(name = "column") @StringEnumeration(value = {"title", "authors", "numPages", "publicationDate", "ratingScore", "numRatings"})
                                    String column,
                                    @RequestParam(name = "sort") @StringEnumeration(value = {"ASC", "DESC"}) String sort) {
        return service.getTopTenBooks(year, column, sort);
    }
}
