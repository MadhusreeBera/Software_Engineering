package com.sismics.books.core.model.jpa;

import java.util.Date;

public class BookDirector {
    BookBuilder bookBuilder;

    public BookDirector(BookBuilder bookBuilder) {
        this.bookBuilder = bookBuilder;
    }

    private Book createGenericBook(String id, String title, String subtitle,
                                String author, String genres, String description,
                                String isbn10, String isbn13, Long pagecount,
                                String language, Date publishDate) {
        return bookBuilder.setId(id).setTitle(title).setSubtitle(subtitle)
                .setAuthor(author).setGenres(genres)
                .setDescription(description).setIsbn10(isbn10)
                .setIsbn13(isbn13).setLanguage(language)
                .setPageCount(pagecount).setPublishDate(publishDate).build();
    }

    public Book createBook(String id, String title, String subtitle,
                           String author, String genres, String description,
                           String isbn10, String isbn13, Long pagecount,
                           String language, Date publishDate) {
        if (bookBuilder instanceof GenericBook) {
            return createGenericBook( id,  title,  subtitle,
                     author,  genres,  description,
                     isbn10,  isbn13, pagecount,
                     language, publishDate);
        }
        return null;
    }
}
