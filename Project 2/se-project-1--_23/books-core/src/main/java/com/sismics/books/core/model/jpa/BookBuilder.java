package com.sismics.books.core.model.jpa;

import javax.persistence.Column;
import java.util.Date;

public abstract class BookBuilder {
    private String id, title, subtitle, author, genres, description, isbn10,
    isbn13, language;
    private Long pageCount;
    private Date publishDate;

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenres() {
        return genres;
    }
    public String getDescription() {
        return description;
    }
    public String getIsbn10() {
        return isbn10;
    }
    public String getIsbn13() {
        return isbn13;
    }
    public Long getPageCount() {
        return pageCount;
    }
    public String getLanguage() {
        return language;
    }
    public Date getPublishDate() {
        return publishDate;
    }
    public BookBuilder setId(String id) {
        this.id = id;
        return this;
    }
    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }
    public BookBuilder setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }
    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }
    public BookBuilder setDescription(String description) {
        this.description = description;
        return this;
    }
    public BookBuilder setGenres(String genres) {
        this.genres = genres;
        return this;
    }
    public BookBuilder setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
        return this;
    }
    public BookBuilder setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }
    public BookBuilder setPageCount(Long pageCount) {
        this.pageCount = pageCount;
        return this;
    }
    public BookBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }
    public BookBuilder setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }
    public Book build() {
        return new Book(this);
    }
}
