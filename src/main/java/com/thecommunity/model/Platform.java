package com.thecommunity.model;

import com.thecommunity.database.UserDatabase;
import com.thecommunity.exceptions.EmailExistException;
import com.thecommunity.exceptions.InvalidPasswordException;

public class Platform {

    private  static Platform platform;
    private UserDatabase userDatabase;

    private Platform(){
        userDatabase = new UserDatabase();
    }

    public static Platform createInstance() {
        if(platform==null){
            platform = new Platform();
        }
        return platform;
    }

    public void register(User user) {
        if(!userDatabase.containsAUser(user.getEmailAddress())){
            userDatabase.add(user);
            user.setLoggedIn();
        }else{
            throw new EmailExistException("Enter another email. Email already exist");
        }
    }

    public int getNumberOfRegisteredUsers() {
        return userDatabase.getNumberOfUsers();
    }

    public User findUserByEmail(String email) {
        return userDatabase.findUser(email);
    }

    public boolean isLogin(String email, String password) {
        if(userDatabase.containsAUser(email)){
            User user = userDatabase.findUser(email);
            user.setLoggedIn();
            return user.getPassword().equals(password);
        }else{
            throw new InvalidPasswordException("Incorrect Password. Enter correct password");
        }
    }

    public void clear() {
        userDatabase.clearDataBase();
    }

    public void logOffUser(User user) {
        user.setLoggedIn();
    }

    public User getUser(String email) {
        return userDatabase.findUser(email);
    }

}
