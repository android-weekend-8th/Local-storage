package com.rathana.local_storage.data.database;

import com.rathana.local_storage.User;

public class UserEntity {

    User user;

    public UserEntity() {
        user = new User();
        user.setName("admin");
        user.setPassword("admin");
    }

    public User getUser() {
        return user;
    }
}
