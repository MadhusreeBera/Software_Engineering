package com.sismics.books.core.model.jpa;

import com.sismics.books.core.dao.jpa.dto.UserBookDto;

public interface FilterCriteria {
    boolean meetsCriteria(UserBookDto book);
}
