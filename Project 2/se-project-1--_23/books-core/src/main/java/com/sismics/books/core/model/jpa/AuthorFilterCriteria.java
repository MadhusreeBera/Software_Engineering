package com.sismics.books.core.model.jpa;

import com.sismics.books.core.dao.jpa.dto.UserBookDto;

import java.util.List;

public class AuthorFilterCriteria implements FilterCriteria {
    private String authors;

    public AuthorFilterCriteria(String authors) {
        this.authors = authors;
    }

    @Override
    public boolean meetsCriteria(UserBookDto book) {

        if (authors.equals(book.getAuthor())) {
            return true;
        }
        return false;
    }
}
