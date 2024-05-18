package com.sismics.books.core.model.jpa;


import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "T_USER_FAVORITE")
public class UserFavorite {
    @Id
    @Column(name = "USE_FAV_ID_C", length = 36)
    private String id;

    //column for user id
    @Column(name = "USE_FAV_USE_ID_C", length = 36)
    private String userId;

    //column for track id
    @Column(name = "USE_FAV_TRA_ID_C", length = 36)
    private String trackId;
    
    //column for artist name
    @Column(name = "USE_FAV_ART_NAME_C", length = 100)
    private String artistName;

    //column for collection name
    @Column(name = "USE_FAV_COL_NAME_C", length = 100)
    private String collectionName;

    //column for track name
    @Column(name = "USE_FAV_TRA_NAME_C", length = 100)
    private String trackName;

    //getters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    //setters
    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("userId", userId)
                .add("trackId", trackId)
                .add("artistName", artistName)
                .add("collectionName", collectionName)
                .add("trackName", trackName)
                .toString();
    }
    
    
}
