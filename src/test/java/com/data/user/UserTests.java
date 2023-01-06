package com.data.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTests {

    @Test
    void createUser() {
        User bingo = new User();
        Assertions.assertEquals(bingo.create("test","111"),"test user added!");
    }

}
