package com.sismics.books.core.model.jpa;

import com.sismics.books.core.dao.jpa.UserBookRatingDao;
import com.sismics.books.core.dao.jpa.dto.UserBookDto;

public class RatingFilterCriteria implements FilterCriteria{
    private int minRating;

    public RatingFilterCriteria(int minRating) {
        this.minRating = minRating;
    }

    @Override
    public boolean meetsCriteria(UserBookDto book) {
        return UserBookRatingDao.getAvgRatingByID(book.getId()) >= minRating;
    }
}
