package com.thecommunity.database;


import com.thecommunity.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserDatabase{

    private final Map<String, User> userDataBank = new ConcurrentHashMap<>();



    public boolean containsAUser(String email){
        return userDataBank.containsKey(email);
    }

    public void add(User user) {
        userDataBank.put(user.getEmailAddress(), user);
    }

    public int getNumberOfUsers() {
        return userDataBank.size();
    }

    public User findUser(String email) {
        if(userDataBank.containsKey(email)){
            return userDataBank.get(email);
        }
        return null;
    }

    public void clearDataBase() {
        userDataBank.clear();
    }
}
