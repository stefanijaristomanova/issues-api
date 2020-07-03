package com.blockverse.issues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableJpaRepositories("com.blockverse.issues.persistence.repository")
@EntityScan("com.blockverse.issues.persistence.entity")

public class IssuesApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuesApplication.class, args);
	}

}
