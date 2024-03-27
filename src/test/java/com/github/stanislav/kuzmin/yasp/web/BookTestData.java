package com.github.stanislav.kuzmin.yasp.web;

import com.github.stanislav.kuzmin.yasp.MatcherFactory;
import com.github.stanislav.kuzmin.yasp.model.Book;

import static com.github.stanislav.kuzmin.yasp.util.DateUtil.convert;
import static com.github.stanislav.kuzmin.yasp.util.NumberUtil.*;

public class BookTestData {

    public static final MatcherFactory.Matcher<Book> BOOK_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Book.class, "id");

    public static Book book1() {
        return Book.builder()
                .isbn("12345")
                .title("Title1")
                .seriesTitle("SeriesTitle1")
                .seriesReleaseTitle("SeriesRelease1")
                .authors("Andy")
                .publisher("Publisher1")
                .language("English")
                .description("Description1")
                .numberOfPages(convertToInteger("100"))
                .format("Format1")
                .genres("Genre1")
                .publicationDate(convert("January 1, 2010"))
                .ratingScore(convertRating("3.95"))
                .numberOfRatings(convertToInteger("13307.0"))
                .numberOfReviews(convertToInteger("339.0"))
                .currentReaders(convertToInteger("181.0"))
                .userInterested(convertToInteger("3961.0"))
                .priceInCents(convertPriceToCents("11.99"))
                .url("https://www.goodreads.com/book/show/title1")
                .build();
    }

    public static Book book2() {
        return Book.builder()
                .isbn("12345")
                .title("Title4")
                .seriesTitle("SeriesTitle4")
                .seriesReleaseTitle("SeriesRelease4")
                .authors("Michael")
                .publisher("Publisher4")
                .language("English")
                .description("Description4")
                .numberOfPages(convertToInteger("103"))
                .format("Format4")
                .genres("Genre4")
                .publicationDate(convert("January 3, 2010"))
                .ratingScore(convertRating("3.95"))
                .numberOfRatings(convertToInteger("13312.0"))
                .numberOfReviews(convertToInteger("343.0"))
                .currentReaders(convertToInteger("184.0"))
                .userInterested(convertToInteger("3964.0"))
                .priceInCents(convertPriceToCents("14.99"))
                .url("https://www.goodreads.com/book/show/title4")
                .build();
    }

    public static Book book3() {
        return Book.builder()
                .isbn("12345")
                .title("Title2")
                .seriesTitle("SeriesTitle2")
                .seriesReleaseTitle("SeriesRelease2")
                .authors("Maria")
                .publisher("Publisher2")
                .language("English")
                .description("Description2")
                .numberOfPages(convertToInteger("101"))
                .format("Format2")
                .genres("Genre2")
                .publicationDate(convert("January 1, 2011"))
                .ratingScore(convertRating("3.95"))
                .numberOfRatings(convertToInteger("13310.0"))
                .numberOfReviews(convertToInteger("341.0"))
                .currentReaders(convertToInteger("182.0"))
                .userInterested(convertToInteger("3962.0"))
                .priceInCents(convertPriceToCents("12.99"))
                .url("https://www.goodreads.com/book/show/title2")
                .build();
    }

    public static Book book4() {
        return Book.builder()
                .isbn("12345")
                .title("Title3")
                .seriesTitle("SeriesTitle3")
                .seriesReleaseTitle("SeriesRelease3")
                .authors("Stanislav")
                .publisher("Publisher3")
                .language("English")
                .description("Description3")
                .numberOfPages(convertToInteger("102"))
                .format("Format3")
                .genres("Genre3")
                .publicationDate(convert("January 1, 2012"))
                .ratingScore(convertRating("3.95"))
                .numberOfRatings(convertToInteger("13311.0"))
                .numberOfReviews(convertToInteger("342.0"))
                .currentReaders(convertToInteger("183.0"))
                .userInterested(convertToInteger("3963.0"))
                .priceInCents(convertPriceToCents("13.99"))
                .url("https://www.goodreads.com/book/show/title3")
                .build();
    }
}
