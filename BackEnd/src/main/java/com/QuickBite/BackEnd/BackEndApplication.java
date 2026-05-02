package com.QuickBite.BackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

	@Bean
	public ApplicationListener<ServletWebServerInitializedEvent> serverListener() {
		return event -> {
			int port = event.getWebServer().getPort();

			System.out.println("==========================");
			System.out.println("PORT: " + port);
			System.out.println("URL : http://localhost:" + port);
			System.out.println("==========================");
		};
	}

}
