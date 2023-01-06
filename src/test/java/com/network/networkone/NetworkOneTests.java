package com.network.networkone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class NetworkOneTests {

    @Test
    void contextLoads() {
        NetworkOne bingo = new NetworkOne();
        Assertions.assertEquals(bingo.getKey(),"ae90bbba41d65b1f047a019e0a55de96");
    }

}
