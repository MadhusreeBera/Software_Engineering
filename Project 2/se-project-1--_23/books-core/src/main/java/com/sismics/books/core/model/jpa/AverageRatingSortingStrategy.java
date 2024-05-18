package com.sismics.books.core.model.jpa;
import com.sismics.books.core.dao.jpa.UserBookRatingDao;
import com.sismics.books.core.dao.jpa.dto.UserBookDto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AverageRatingSortingStrategy implements SortingStrategy{
    @Override
    public List<UserBookDto> sortBooks(List<UserBookDto> booksList) {
        List<UserBookDto> sortedBooksList = booksList;

        Collections.sort(sortedBooksList, new Comparator<UserBookDto>() {
            @Override
            public int compare(UserBookDto b1, UserBookDto b2) {
                return Double.compare(UserBookRatingDao.getAvgRatingByID(b1.getId()),
                        UserBookRatingDao.getAvgRatingByID(b2.getId()));
            }
        });
        return sortedBooksList;
    }
}
