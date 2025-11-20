package com.spring.cosumption.spring_consumption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.spring.cosumption.spring_consumption.proxy")
public class SpringConsumptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConsumptionApplication.class, args);
	}

}
