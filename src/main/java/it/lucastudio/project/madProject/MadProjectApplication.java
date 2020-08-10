package it.lucastudio.project.madProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.lucastudio.project.*")
@EnableFeignClients
public class MadProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadProjectApplication.class, args);
	}

}
