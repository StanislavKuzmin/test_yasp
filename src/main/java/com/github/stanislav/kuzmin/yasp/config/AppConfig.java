package com.github.stanislav.kuzmin.yasp.config;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import com.github.stanislav.kuzmin.yasp.error.AppInitException;
import com.github.stanislav.kuzmin.yasp.model.Book;
import com.github.stanislav.kuzmin.yasp.repository.BookRepository;
import com.github.stanislav.kuzmin.yasp.util.JsonUtil;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ProblemDetail;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.github.stanislav.kuzmin.yasp.util.DateUtil.convert;
import static com.github.stanislav.kuzmin.yasp.util.NumberUtil.*;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AppConfig {

    @Value("${csv.file.url}")
    private String filePath;

    private final ResourceLoader resourceLoader;

    private final BookRepository repository;

    @Bean
    public ApplicationRunner dataloader() {
        return args -> {
            List<Book> books = new ArrayList<>();
            log.info("Start reading data from file={}", filePath);
            try (InputStream inputStream = resourceLoader.getResource(filePath).getInputStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 CSVReader csvReader = new CSVReader(inputStreamReader)) {
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
        };
    }

    @JsonAutoDetect(fieldVisibility = NONE, getterVisibility = ANY)
    interface MixIn {
        @JsonAnyGetter
        Map<String, Object> getProperties();
    }

    @Autowired
    void configureAndStoreObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new Hibernate5JakartaModule());
        objectMapper.addMixIn(ProblemDetail.class, MixIn.class);
        JsonUtil.setMapper(objectMapper);
    }
}
