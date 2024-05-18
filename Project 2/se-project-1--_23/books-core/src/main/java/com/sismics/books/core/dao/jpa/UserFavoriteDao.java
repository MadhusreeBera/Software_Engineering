// package com.sismics.books.core.dao.jpa;

// import java.util.UUID;

// import javax.management.Query;
// import javax.persistence.EntityManager;

// import com.sismics.books.core.model.jpa.UserFavorite;
// import com.sismics.util.context.ThreadLocalContext;

// public class UserFavoriteDao {
//     public String saveFavorite(UserFavorite uf) throws Exception{
        
//         EntityManager em = ThreadLocalContext.get().getEntityManager();
//         uf.setId(UUID.randomUUID().toString());
//         em.persist(uf);
//         return uf.getId();
//     }

//     public void deleteFavorite(String userId, String trackID) throws Exception{
//         EntityManager em = ThreadLocalContext.get().getEntityManager();
       
//         //query to find favorite track matching user id and track id
//         Query q = (Query) em.createQuery("select uf from UserFavorite uf where uf.userId = :userId and uf.trackId = :trackId");
//         q.setParameter("userId", userId);
//         q.setParameter("trackId", trackID);
//         UserFavorite uf = (UserFavorite) q.getSingleResult();

        
       
//         UserFavorite uf = em.find(UserFavorite.class, id);
//         em.remove(uf);
//     }
// }
