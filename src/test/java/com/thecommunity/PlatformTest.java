package com.thecommunity;

import com.thecommunity.exceptions.EmailExistException;
import com.thecommunity.model.Platform;
import com.thecommunity.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

public class PlatformTest {

    private static Platform newPlatform;
    @BeforeEach
    void startAllTestWithThis(){
        newPlatform = Platform.createInstance();
    }

    @AfterEach
    void doThisAfterEachTest(){
        newPlatform.clear();
        newPlatform=null;
    }

    @Test
    void thatPlatformCanBeCreated(){
        assertThat(newPlatform, is(notNullValue()));
    }

    @Test
    void thatOnlyOneInstanceOfPlatformCanBeCreated(){
        Platform platformOne = Platform.createInstance();
        Platform platformTwo = Platform.createInstance();
        assertThat(platformOne, is(platformTwo));
        assertThat(platformTwo, is(newPlatform));
    }

    @Test
    void thatUserCanRegister(){
        User user = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        newPlatform.register(user);
        assertEquals(1, newPlatform.getNumberOfRegisteredUsers());
        assertNotNull(newPlatform.findUserByEmail("tobi@gmail.com"));
    }

    @Test
    void thatPlatform_Throws_AnExceptionIfUserRegisterWithAnEmailAlreadyOnTheSystem(){
        User user1 = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        User user2 = new User("Oluwatosin", "Jolayemi", "tobi@gmail.com");
        newPlatform.register(user1);
        //when
        assertThrows(EmailExistException.class, ()->newPlatform.register(user2));
    }

    @Test
    void thatUserCanLogin(){
        User user = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        newPlatform.register(user);
        user.setPassword("12345");
        assertTrue(newPlatform.isLogin("tobi@gmail.com", "12345"));
    }

    @Test
    void thatUserCanLogOff(){
        User user = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        newPlatform.register(user);
        newPlatform.logOffUser(user);
        assertTrue(newPlatform.getUser("tobi@gmail.com").getLoginStatus());
    }

    @Test
    void thatUserCanSendFriendRequest(){
        User user = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        User user1 = new User("Oluwatosin", "Jolayemi", "tobitobi@gmail.com");
        newPlatform.register(user);
        newPlatform.register(user1);
        user.sendFriendRequestTo(user1);
        assertThat(user.getNumberOfSentFriendRequest(), is(1));
        assertThat(user1.getNumberOfReceivedFriendRequest(), is(1));

    }

    @Test
    void thatUserCanAcceptFriendRequest(){
        User user = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        User user1 = new User("Oluwatosin", "Jolayemi", "tobitobi@gmail.com");
        newPlatform.register(user);
        newPlatform.register(user1);
        user.sendFriendRequestTo(user1);
        user1.acceptFriendRequestFrom(user);
        assertEquals(0, user.getNumberOfSentFriendRequest());
        assertEquals(0, user1.getNumberOfReceivedFriendRequest());
        assertEquals(1, user.getNumberOfFriends());
        assertEquals(1, user1.getNumberOfFriends());
    }

    @Test
    void thatUserCanRejectFriendRequest(){
        User user = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        User user1 = new User("Oluwatosin", "Jolayemi", "tobitobi@gmail.com");
        newPlatform.register(user);
        newPlatform.register(user1);
        user.sendFriendRequestTo(user1);
        user1.rejectFriendRequest(user);
        assertEquals(0, user1.getNumberOfReceivedFriendRequest());
        assertEquals(0, user1.getNumberOfFriends());
    }

    @Test
    void thatUserCannotSendMoreThanOneFriendRequestToOnePerson(){
        User user = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        User user1 = new User("Oluwatosin", "Jolayemi", "tobitobi@gmail.com");
        newPlatform.register(user);
        newPlatform.register(user1);
        user.sendFriendRequestTo(user1);
        user.sendFriendRequestTo(user1);
        assertEquals(1, user1.getNumberOfReceivedFriendRequest());
    }

    @Test
    void thatUserCanSendChat(){
        User user = new User("Oluwatobi", "Jolayemi", "tobi@gmail.com");
        User user1 = new User("Oluwatosin", "Jolayemi", "tobitobi@gmail.com");
        newPlatform.register(user);
        newPlatform.register(user1);
    }
}
