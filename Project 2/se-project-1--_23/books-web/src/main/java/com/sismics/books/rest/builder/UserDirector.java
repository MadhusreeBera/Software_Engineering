package com.sismics.books.rest.builder;

import java.util.Date;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.sismics.books.core.constant.Constants;

public class UserDirector {
    private UserBuilder userBuilder;

    public UserDirector(UserBuilder userBuilder) {
        this.userBuilder = userBuilder;
    }

    public void construct(String username, String password, String email){
        userBuilder.buildUsername(username);
        userBuilder.buildPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userBuilder.buildEmail(email);
        userBuilder.buildLocaleId(Constants.DEFAULT_LOCALE_ID);
        userBuilder.buildRoleId(Constants.DEFAULT_USER_ROLE);
        userBuilder.buildCreateDate(new Date());
        userBuilder.buildId(UUID.randomUUID().toString());
        userBuilder.buildTheme(Constants.DEFAULT_THEME_ID);
    }   
}
