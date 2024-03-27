package com.github.stanislav.kuzmin.yasp.web;

import com.github.stanislav.kuzmin.yasp.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.stanislav.kuzmin.yasp.web.BookTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest extends AbstractControllerTest {

    private final String REST_URL = BookController.REST_URL;

    @Test
    void getTopTenBooksWithYear() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("column", "numPages").param("year", "2010").param("sort", "DESC"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BOOK_MATCHER.contentJson(book2(), book1()));
    }

    @Test
    void getTopTenBooksWithoutYear() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("column", "publicationDate").param("sort", "ASC"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BOOK_MATCHER.contentJson(book1(), book2(), book3(), book4()));
    }

    @Test
    void getTopTenBooksInvalidYear() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("column", "publicationDate").param("year", "17890").param("sort", "ASC"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getTopTenBooksInvalidColumn() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("column", "id").param("sort", "ASC"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getTopTenBooksInvalidSort() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("column", "publicationDate").param("sort", "qwerty"))
                .andExpect(status().isUnprocessableEntity());
    }
}