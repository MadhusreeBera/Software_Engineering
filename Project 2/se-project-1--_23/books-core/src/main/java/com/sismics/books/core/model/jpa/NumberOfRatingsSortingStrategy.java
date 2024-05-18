package com.sismics.books.core.model.jpa;
import com.sismics.books.core.dao.jpa.UserBookRatingDao;
import com.sismics.books.core.dao.jpa.dto.UserBookDto;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
public class NumberOfRatingsSortingStrategy implements SortingStrategy{
    @Override
    public List<UserBookDto> sortBooks(List<UserBookDto> booksList) {
        List<UserBookDto> sortedBooksList = booksList;

        Collections.sort(sortedBooksList, new Comparator<UserBookDto>() {
            @Override
            public int compare(UserBookDto b1, UserBookDto b2) {
                return Long.compare(UserBookRatingDao.getNumRatingsByID(b1.getId()),
                        UserBookRatingDao.getNumRatingsByID(b2.getId()));
            }
        });
        return sortedBooksList;
    }
}
