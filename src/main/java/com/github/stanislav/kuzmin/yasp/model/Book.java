package com.github.stanislav.kuzmin.yasp.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.util.ProxyUtils;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Access(AccessType.FIELD)
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String isbn;

    private String title;

    private String seriesTitle;

    private String seriesReleaseTitle;

    @Column(columnDefinition = "TEXT")
    private String authors;

    private String publisher;

    private String language;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer numberOfPages;

    private String format;

    @Column(columnDefinition = "TEXT")
    private String genres;

    @Column(columnDefinition = "DATE")
    private LocalDate publicationDate;

    private Double ratingScore;

    private Integer numberOfRatings;

    private Integer numberOfReviews;

    private Integer currentReaders;

    private Integer userInterested;

    private Integer priceInCents;

    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(ProxyUtils.getUserClass(o))) {
            return false;
        }
        Book book = (Book) o;
        return id != null && id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
