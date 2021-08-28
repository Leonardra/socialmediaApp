package com.thecommunity.model;

public class FriendMatcher {

    public static void match(FriendRequest friendRequest){
        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();
        sender.addFriend(receiver);
        sender.removeFromSentFriendRequest(friendRequest);
        receiver.addFriend(sender);
        receiver.removeFromReceivedFriendRequest(friendRequest);

    }


}
