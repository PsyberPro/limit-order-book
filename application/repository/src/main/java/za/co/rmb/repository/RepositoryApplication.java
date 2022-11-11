package za.co.rmb.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RepositoryApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RepositoryApplication.class, args);
	}

}

