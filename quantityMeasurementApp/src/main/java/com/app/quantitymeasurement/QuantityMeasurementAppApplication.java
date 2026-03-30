package com.app.quantitymeasurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
/*@ComponentScan(basePackages = "com.app")
@EnableJpaRepositories(basePackages = "com.app.repository")
@EntityScan(basePackages = "com.app.model")*/
public class QuantityMeasurementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuantityMeasurementAppApplication.class, args);
	}
}