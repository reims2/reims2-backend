package org.pvh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class PVHApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PVHApplication.class, args);
	}
}
