package com.sismics.books.core.model.jpa;

import com.sismics.books.core.dao.jpa.dto.UserBookDto;
import com.sismics.books.core.model.jpa.Book;

import java.util.ArrayList;
import java.util.List;

public class Library{

    private static Library library = new Library();

    private List<UserBookDto> booksList;

    public void setBooksList(List<UserBookDto> booksList) {
        this.booksList = booksList;
    }

    private Library(){
    }

    public static Library getInstance(){
        return library;
    }

    public List<UserBookDto> getBooksList(){
        return booksList;
    }

}