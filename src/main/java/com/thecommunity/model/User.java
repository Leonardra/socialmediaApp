package com.thecommunity.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private boolean loginStatus;
    private List<FriendRequest> receivedFriendRequests;
    private List<FriendRequest> sentFriendRequests;
    private List<User> friends;

    public User(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        receivedFriendRequests = new ArrayList<>();
        sentFriendRequests = new ArrayList<>();
        friends = new ArrayList<>();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginStatus() {
        if(!loginStatus){
            loginStatus = true;
        }else{
            loginStatus = false;
        }
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }

    public void sendFriendRequestTo(User receiver) {
        FriendRequest friendRequest = new FriendRequest(this, receiver);
        boolean isExist = false;
        for(FriendRequest request: sentFriendRequests){
            if(request.getReceiver().equals(receiver)) {
                isExist = true;
                break;
            }
        }
        if(!isExist){
        this.addToSentFriendRequest(friendRequest);
        receiver.addToReceivedFriendRequest(friendRequest);
        }
    }

    private void addToReceivedFriendRequest(FriendRequest friendRequest) {
        receivedFriendRequests.add(friendRequest);
    }

    private void addToSentFriendRequest(FriendRequest friendRequest) {
        sentFriendRequests.add(friendRequest);
    }

    public int getNumberOfSentFriendRequest() {
        return sentFriendRequests.size();
    }

    public int getNumberOfReceivedFriendRequest() {
        return receivedFriendRequests.size();
    }


    public void acceptFriendRequestFrom(User sender) {
        for (FriendRequest request: receivedFriendRequests) {
            if(request.getSender().equals(sender)){
                FriendMatcher.match(request);
                break;
            }
        }
    }

    public void removeFromReceivedFriendRequest(FriendRequest friendRequest) {
        receivedFriendRequests.removeIf(request -> request ==friendRequest);
    }

    public void removeFromSentFriendRequest(FriendRequest friendRequest) {
        sentFriendRequests.removeIf(request -> request ==friendRequest);
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public int getNumberOfFriends() {
        return friends.size();
    }


    public void rejectFriendRequest(User user) {
        for(FriendRequest request: receivedFriendRequests){
            if(request.getSender().equals(user)){
                this.removeFromReceivedFriendRequest(request);
                break;
            }
        }
    }
}
