package com.thecommunity.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    List<Message> messages = new ArrayList<>();

    public void add(String sender, String message) {
        Message newMessage = new Message(sender, message);
        messages.add(newMessage);
    }

    public List<String> returnMessages() {
        List<String> displayMessages = new ArrayList<>();
        for (Message message: messages){
            displayMessages.add(message.toString());
        }
        return displayMessages;
    }
}
