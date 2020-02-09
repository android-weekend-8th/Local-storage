package com.rathana.local_storage.data.database;

public class InMemoryDatabase {

    private InMemoryDatabase() {
    }

    public static InMemoryDatabase newInstance() {
        return new InMemoryDatabase();
    }

    public UserEntity getUserEntity() {
        return new UserEntity();
    }
}
