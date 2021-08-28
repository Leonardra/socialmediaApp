package com.thecommunity.model;

public class FriendRequest {

    private final User sender;
    private final User receiver;

    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public User getSender() {
            return sender;
    }

    public User getReceiver() {
        return receiver;
    }
}
