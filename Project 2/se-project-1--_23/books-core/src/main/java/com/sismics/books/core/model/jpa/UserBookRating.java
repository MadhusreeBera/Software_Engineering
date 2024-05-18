package com.sismics.books.core.model.jpa;

import javax.persistence.*;

@Entity
@Table(name = "T_USER_BOOK_RATING")
public class UserBookRating {

    /**
     * UserBookRating ID.
     */
    @Id
    @Column(name = "UBR_ID_C", length = 36)
    private String id;

    @Column(name = "UBR_USERID_C", length = 36)
    private String user_id;

    @Column(name = "UBR_BOOKID_C", length = 36)
    private String book_id;

    @Column(name = "UBR_RATING_C")
    private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}