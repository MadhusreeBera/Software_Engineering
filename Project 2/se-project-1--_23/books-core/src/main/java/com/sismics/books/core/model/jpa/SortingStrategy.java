package com.sismics.books.core.model.jpa;

import com.sismics.books.core.dao.jpa.dto.UserBookDto;

import java.util.List;

public interface SortingStrategy {
    public List<UserBookDto> sortBooks(List<UserBookDto> booksList);
}
