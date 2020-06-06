package org.algoritmed.ows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:config-app-spring.xml")
public class OwsAlgoritmedApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwsAlgoritmedApplication.class, args);
	}

}
