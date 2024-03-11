package com.hierarchy.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})//não valida user e password
public class HierarchyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HierarchyProjectApplication.class, args);
	}

}
