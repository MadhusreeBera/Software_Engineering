package com.sismics.books.core.dao.jpa;

import com.sismics.books.core.model.jpa.UserBookRating;
import com.sismics.util.context.ThreadLocalContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserBookRatingDao {
    public void addRating(UserBookRating userBookRating) throws Exception {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        Query q = em.createQuery("select ubr from UserBookRating ubr where ubr" +
                ".user_id" +
                " = :user_id and ubr.book_id = :book_id");
        q.setParameter("user_id", userBookRating.getUser_id());
        q.setParameter("book_id", userBookRating.getBook_id());
        List<?> l = q.getResultList();

        if (l.size() > 0) {
            throw new Exception("RatingAlreadyExists");
        }

        em.persist(userBookRating);
    }

    public static Double getAvgRatingByID(String book_id) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        Query q = em.createQuery("select avg(ubr.rating) from UserBookRating ubr " +
                "where" +
                " ubr.book_id = :book_id");
        q.setParameter("book_id", book_id);

        Double avg_rating = (q.getSingleResult() != null ?
                (Double) q.getSingleResult() : 0);
        System.out.println("Book ID: " + book_id);
        System.out.println("Avg Rating: " + avg_rating);
        return avg_rating;
    }

    public static Long getNumRatingsByID(String book_id) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        Query q = em.createQuery("select count(ubr.rating) from UserBookRating ubr" +
                " " +
                "where" +
                " ubr.book_id = :book_id");
        q.setParameter("book_id", book_id);
        Long num_ratings = (q.getSingleResult() != null ?
                (Long) q.getSingleResult() : 0);
        System.out.println("Book ID: " + book_id);
        System.out.println("Num Ratings: " + num_ratings);
        return num_ratings;
    }
}
