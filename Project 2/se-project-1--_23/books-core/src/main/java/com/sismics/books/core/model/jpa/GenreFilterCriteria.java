package com.sismics.books.core.model.jpa;

import com.sismics.books.core.dao.jpa.dto.UserBookDto;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class GenreFilterCriteria implements FilterCriteria{
    private List<String> genres;

    public GenreFilterCriteria(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public boolean meetsCriteria(UserBookDto book) {
        String[] items = book.getGenres().split(",");
        List<String> bookGenres = Arrays.asList(items);
        System.out.println("BookGenresList:" + bookGenres);
        for (String a: bookGenres) {
            System.out.println("iter");
            System.out.println(a);
            if (genres.contains(a)) {
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;
    }
}
