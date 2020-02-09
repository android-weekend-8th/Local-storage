package com.rathana.local_storage.service;

import android.content.Context;

import com.rathana.local_storage.User;
import com.rathana.local_storage.data.UserSessionPref;
import com.rathana.local_storage.data.database.InMemoryDatabase;

public class UserService {

    Context context;
    private UserSessionPref userSessionPref;
    private InMemoryDatabase database;

    public UserService(Context context) {
        this.context = context;
        userSessionPref = new UserSessionPref(context);
        database = InMemoryDatabase.newInstance();
    }

    public void saveUser(String name, String password) {
        userSessionPref.save(name, password);
    }

    public boolean login(String name, String password) {
        User user = database.getUserEntity().getUser();
        if (name.equals(user.getName()) &&
                password.equals(user.getPassword())) {
            //todo login
            userSessionPref.save(name, password);
            return true;
        }

        return false;
    }

    public User getUser() {
        return userSessionPref.getUser();
    }

    public void logout() {
        userSessionPref.remove();
    }


}

