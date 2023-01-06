package com.service.endpointone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EndpointOneApplicationTests {

	@Test
	void contextLoads() {
		EndpointOneApplication bingo = new EndpointOneApplication();
		Assertions.assertEquals(bingo.hash(),123);
	}

}
