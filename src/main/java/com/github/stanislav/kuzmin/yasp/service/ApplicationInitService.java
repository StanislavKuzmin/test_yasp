package com.github.stanislav.kuzmin.yasp.service;

import com.github.stanislav.kuzmin.yasp.error.AppInitException;
import com.github.stanislav.kuzmin.yasp.model.Book;
import com.github.stanislav.kuzmin.yasp.repository.BookRepository;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static com.github.stanislav.kuzmin.yasp.util.DateUtil.*;
import static com.github.stanislav.kuzmin.yasp.util.NumberUtil.*;

@Service
@RequiredArgsConstructor
public class ApplicationInitService implements ApplicationRunner {

    private final BookRepository repository;

    @Value("${csv.file.url}")
    private String filePath;

    @Override
    public void run(ApplicationArguments args) {
        List<Book> books = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
             CSVReader csvReader = new CSVReader(fileReader)) {
            csvReader.readNext();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                Book book = Book.builder()
                        .isbn(nextRecord[1])
                        .title(nextRecord[2])
                        .seriesTitle(nextRecord[3])
                        .seriesReleaseTitle(nextRecord[4])
                        .authors(nextRecord[5])
                        .publisher(nextRecord[6])
                        .language(nextRecord[7])
                        .description(nextRecord[8])
                        .numberOfPages(convertToInteger(nextRecord[9]))
                        .format(nextRecord[10])
                        .genres(nextRecord[11])
                        .publicationDate(convert(nextRecord[12]))
                        .ratingScore(convertRating(nextRecord[13]))
                        .numberOfRatings(convertToInteger(nextRecord[14]))
                        .numberOfReviews(convertToInteger(nextRecord[15]))
                        .currentReaders(convertToInteger(nextRecord[16]))
                        .userInterested(convertToInteger(nextRecord[17]))
                        .priceInCents(convertPriceToCents(nextRecord[18]))
                        .url(nextRecord[19])
                        .build();
                books.add(book);
            }
            repository.saveAll(books);
        } catch (Exception e) {
            throw new AppInitException(e.getMessage());
        }
    }
}
