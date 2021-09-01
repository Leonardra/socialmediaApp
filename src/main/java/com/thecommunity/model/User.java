package com.thecommunity.model;

import com.thecommunity.exceptions.FriendsNotFoundException;
import lombok.Getter;

import java.util.*;


@Getter
public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private boolean isLoggedIn;
    private List<FriendRequest> receivedFriendRequests;
    private List<FriendRequest> sentFriendRequests;
    private List<User> friends;
    private final Map<String, Chat> chats = new HashMap<>();

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

    public void setLoggedIn() {
        if(!isLoggedIn){
            isLoggedIn = true;
        }else{
            isLoggedIn = false;
        }
    }

    public boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void sendFriendRequestTo(User receiver) {
        if(isLoggedIn){
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
        if(isLoggedIn){
            for (FriendRequest request: receivedFriendRequests) {
                if(request.getSender().equals(sender)){
                    FriendMatcher.match(request);
                    break;
                }
            }
        }
    }

    public void removeFromReceivedFriendRequest(FriendRequest friendRequest) {
        if(isLoggedIn){
        receivedFriendRequests.removeIf(request -> request ==friendRequest);
        }
    }

    public void removeFromSentFriendRequest(FriendRequest friendRequest) {
        if(isLoggedIn){
        sentFriendRequests.removeIf(request -> request ==friendRequest);
        }
    }

    public void addFriend(User user) {
        if(isLoggedIn){
            friends.add(user);
        }
    }

    public int getNumberOfFriends() {
        return friends.size();
    }


    public void rejectFriendRequest(User user) {
        if(isLoggedIn){
            for(FriendRequest request: receivedFriendRequests){
                if(request.getSender().equals(user)){
                    removeFromReceivedFriendRequest(request);
                    break;
                }
            }
        }
    }

    public void send(String message, String recipientEmail) {
        if(isLoggedIn){
            if(findFriend(recipientEmail) != null){
            Chat chat = createChatFor(recipientEmail);
            chat.add(message, this.emailAddress);
            }else{
                throw new FriendsNotFoundException();
            }

        }
    }

    private Chat createChatFor(String recipientEmail) {
        if(chats.containsKey(recipientEmail)){
            return chats.get(recipientEmail);
        }
        Chat chat = new Chat();
        User recipient = findFriend(recipientEmail);
        recipient.chats.put(getEmailAddress(), chat);
        chats.put(recipientEmail, chat);
        return chat;
    }

    public Chat getChatsWith(String recipient) {
        return chats.get(recipient);
    }

    public User findFriend(String emailAddress) {
        User found = null;
        for(User friend: friends){
            if(Objects.equals(friend.getEmailAddress(), emailAddress)){
                found = friend;
            }
        }
        return found;
    }
}
