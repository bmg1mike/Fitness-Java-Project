package com.test.youtube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.test.youtube.user.UserHttpClient;
import com.test.youtube.user.UserRestClient;

@SpringBootApplication
public class YoutubeApplication {

	public static void main(String[] args) {

		final Logger log = LoggerFactory.getLogger(YoutubeApplication.class);
		SpringApplication.run(YoutubeApplication.class, args);
		log.info("Application started successfully");
	}

	@Bean
	UserHttpClient userHttpClient() {
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}

	@Bean
	CommandLineRunner runner(UserHttpClient restClient) {
		return args -> {
			
			var users = restClient.getUsers();
			System.out.println("Users: " + users);
		};
	}

}
