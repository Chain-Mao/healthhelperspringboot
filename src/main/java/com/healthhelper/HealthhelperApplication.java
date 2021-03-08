package com.healthhelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.healthhelper")
public class HealthhelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthhelperApplication.class, args);
	}

}
