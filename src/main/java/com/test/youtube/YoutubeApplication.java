package com.test.youtube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YoutubeApplication {

	public static void main(String[] args) {

		final Logger log = LoggerFactory.getLogger(YoutubeApplication.class);
		SpringApplication.run(YoutubeApplication.class, args);
		log.info("Application started successfully");
	}

	// @Bean
	// CommandLineRunner runner(RunRepository repository) {
	// return args -> {
	// Run run = new Run(1, "Morning Run", LocalDateTime.now(),
	// LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5,
	// Location.Outdoor);
	// repository.createRun(run);
	// };
	// }

}
