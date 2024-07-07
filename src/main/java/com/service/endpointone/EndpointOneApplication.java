package com.service.endpointone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.network.networkone.NetworkOne;
import com.data.user.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

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
		return "Here is the weather in Texas as fetched from openweathermap using an API key that " +
			"is securely stored in the GCP secret manager:<br><br>" + new NetworkOne().fetch();
	}

	@GetMapping("/user")
	public String user(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone) {
		User user = new User();
		if ( name != null && phone != null)
		user.create(name,phone);
		return user.fetch();
	}

	@CrossOrigin()
	@GetMapping("/people")
	public String people(
			@RequestParam(value = "crowd", defaultValue = "100") String crowd
	) throws InterruptedException {
		ArrayList people = new ArrayList<String>();
		for (int i = 0; i <= Integer.parseInt(crowd); i++) {
			if (Math.random() * 100 > 10) people.add(new Doctor().toString());
		}
		java.util.concurrent.TimeUnit.SECONDS.sleep(1);
		return people.toString();
	}

	public int hash() {
		return 123;
	}
}

class Doctor {
	double age = Math.round(Math.random() * 100);
	String name = names.get((int)Math.round(Math.random() * 99));

	@Override
	public String toString() {
		return "{\"name\":\""+name+"\",\"age\":\""+age+"\"}";
	}

	static ArrayList<String> names = new ArrayList<String>() {
		{
			add("Fred");
			add("Sam");
			add("Joe");
			add("Jim");
			add("Mike");
			add("Theo");
			add("Atticus");
			add("Silas");
			add("Oliver");
			add("Hugo");
			add("Oscar");
			add("Milo");
			add("August");
			add("Atlas");
			add("Otto");
			add("Kylian");
			add("Caspian");
			add("Cassius");
			add("Arlo");
			add("Felix");
			add("Royal");
			add("Jason");
			add("Soren");
			add("Jude");
			add("Cyrus");
			add("Jacob");
			add("Matthew");
			add("Joshua");
			add("Christopher");
			add("Nicholas");
			add("Andrew");
			add("Joseph");
			add("Daniel");
			add("Tyler");
			add("William");
			add("Bill");
			add("Brandon");
			add("Ryan");
			add("John");
			add("Zachary");
			add("David");
			add("Anthony");
			add("James");
			add("Justin");
			add("Alex");
			add("Jonathan");
			add("Christian");
			add("Austin");
			add("Dylan");
			add("Ethan");
			add("Benjamin");
			add("Noah");
			add("Samuel");
			add("Robert");
			add("Emily");
			add("Hannah");
			add("Madison");
			add("Ashley");
			add("Sarah");
			add("Alexis");
			add("Samantha");
			add("Jessica");
			add("Elizabeth");
			add("Taylor");
			add("Lauren");
			add("Alyssa");
			add("Kayla");
			add("Abigail");
			add("Brianna");
			add("Olivia");
			add("Emma");
			add("Megan");
			add("Grace");
			add("Victoria");
			add("Rachel");
			add("Anna");
			add("Sydney");
			add("Destiny");
			add("Morgan");
			add("Jennifer");
			add("Jasmine");
			add("Haley");
			add("Alexandra");
			add("Savannah");
			add("Chloe");
			add("Rebecca");
			add("Stephanie");
			add("Maria");
			add("Sophia");
			add("Mackenzie");
			add("Allison");
			add("Isabella");
			add("Mary");
			add("Amber");
			add("Danielle");
			add("Gabrielle");
			add("Jordan");
			add("Brooke");
			add("Michelle");
			add("Sierra");
		}
	};
}
