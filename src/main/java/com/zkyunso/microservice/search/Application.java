package com.zkyunso.microservice.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.zkyunso.microservice.http.util.HttpRest;
import com.zkyunso.microservice.http.util.SpringRestImp;
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Application {
	@Bean
	public HttpRest httpRest() {
		return new SpringRestImp();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
