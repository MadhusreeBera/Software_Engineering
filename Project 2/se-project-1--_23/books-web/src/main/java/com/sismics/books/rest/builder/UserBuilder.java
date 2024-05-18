package com.sismics.books.rest.builder;
import java.util.Date;

public interface UserBuilder {
    public abstract void buildId(String id);
    public abstract void buildLocaleId(String localeId);
    public abstract void buildRoleId(String roleId);
    public abstract void buildUsername(String username);
    public abstract void buildPassword(String password);
    public abstract void buildEmail(String email);
    public abstract void buildTheme(String theme);
    public abstract void buildFirstConnection(boolean firstConnection);
    public abstract void buildCreateDate(Date createDate);
    public abstract void buildDeleteDate(Date deleteDate);
    
}
