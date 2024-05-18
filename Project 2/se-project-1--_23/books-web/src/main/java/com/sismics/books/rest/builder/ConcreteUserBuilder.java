package com.sismics.books.rest.builder;

import java.util.Date;

import com.sismics.books.core.model.jpa.User;

public class ConcreteUserBuilder implements UserBuilder{
    private User user;
    public ConcreteUserBuilder() {
        user = new User();
    }
    @Override
    public void buildId(String id) {
        user.setId(id);
    }

    @Override
    public void buildLocaleId(String localeId) {
        user.setLocaleId(localeId);
    }

    @Override
    public void buildRoleId(String roleId) {
        user.setRoleId(roleId);
    }

    @Override
    public void buildUsername(String username) {
        user.setUsername(username);
    }

    @Override
    public void buildPassword(String password) {
        user.setPassword(password);
    }

    @Override
    public void buildEmail(String email) {
        user.setEmail(email);
    }

    @Override
    public void buildTheme(String theme) {
        user.setTheme(theme);
    }

    @Override
    public void buildFirstConnection(boolean firstConnection) {
        user.setFirstConnection(firstConnection);
    }

    @Override
    public void buildCreateDate(Date createDate) {
        user.setCreateDate(createDate);
    }

    @Override
    public void buildDeleteDate(Date deleteDate) {
        user.setDeleteDate(deleteDate);
    }
    public User getResult() {
        return user;
    }
    
}
