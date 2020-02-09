package com.rathana.local_storage.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.rathana.local_storage.User;

public class UserSessionPref {

    private Context context;

    public UserSessionPref(Context context) {
        this.context = context;
    }

    static final String USER_PREF = "user_pref";
    static final String KEY_USER_NAME = "name";
    static final String KEY_USER_PASSWORD = "password";
    static final String KEY_USER_IS_LOGIN = "is_login";


    private SharedPreferences getPref() {
        return context.getSharedPreferences(
                USER_PREF,
                Context.MODE_PRIVATE
        );
    }

    public void save(String username, String password) {
        SharedPreferences.Editor editor = getPref().edit();
        editor.putString(KEY_USER_NAME, username);
        editor.putString(KEY_USER_PASSWORD, password);
        editor.apply();
    }

    public User getUser() {
        SharedPreferences pref = getPref();
        String name = pref.getString(KEY_USER_NAME, null);
        String pass = pref.getString(KEY_USER_PASSWORD, null);
        if (name != null && pass != null) {
            User user = new User();
            user.setName(name);
            user.setPassword(pass);
            return user;
        }
        return null;
    }

    public void remove() {
        getPref().edit().clear().apply();
    }
}
