package com.service.endpointone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.network.networkone.NetworkOne;
import com.data.user.User;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
@RestController
public class EndpointOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndpointOneApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return String.format(
			"Welcome to this <b>Java</b> microservice!<br>" +
			"This service was created using <b>Spring Initializer</b> and packaged using <b>Maven</b>.<br>" +
			"It was containerized using <b>Docker</b>.<br>" +
			"It is hosted using <b>GCP Cloud Run</b>.<br>" +
			"It was previously hosted using <b>AWS Elastic Beanstalk</b>.<br>"
		);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello " + name);
	}

	@GetMapping("/weather")
	public String weather() throws URISyntaxException, IOException, InterruptedException {
		return "Here is the weather in Texas:<br><br>" + new NetworkOne().fetch();
	}

	@GetMapping("/user")
	public String user(
			@RequestParam(value = "name", defaultValue = "michael") String name,
			@RequestParam(value = "phone", defaultValue = "123") String phone) {
		User user = new User();
		user.create(name,phone);
		return user.fetch();
	}

	public int hash() {
		return 123;
	}
}
