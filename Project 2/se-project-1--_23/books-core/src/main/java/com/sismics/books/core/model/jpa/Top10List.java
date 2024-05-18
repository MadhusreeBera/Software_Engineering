package com.sismics.books.core.model.jpa;

import com.sismics.books.core.dao.jpa.dto.UserBookDto;
import java.util.List;

public class Top10List {
    public List<UserBookDto> getTop10Books(List<UserBookDto> booksList,
                                           SortingStrategy strategy){

        List<UserBookDto> sortedBooksList = strategy.sortBooks(booksList);
        return sortedBooksList.subList(0, Math.min(sortedBooksList.size(), 10));
    }
}
