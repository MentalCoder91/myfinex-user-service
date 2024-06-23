package com.expleo.user_service;

import com.expleo.user_service.entity.User;
import com.expleo.user_service.mapper.UserMapper;
import com.expleo.user_service.service.UserService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "User microservice REST API Documentation",
				description = "MyFinEx User microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Anish Alwekar",
						email = "anish.alwekar@gmail.com",
						url = "https://github.com/MentalCoder91"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/MentalCoder91"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "MyFinEx User microservice REST API Documentation",
				url = "http://localhost:9091/swagger-ui/index.html"
		)
)
@EnableFeignClients
public class UserServiceApplication implements CommandLineRunner {


	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		User user1 = new User(1L,"anishA","anish@123","anish.alwekar@yahoo.com","1234567890");

		User user2 = new User(2L,"ruchitaA","ruchita@123","ruchita.alwekar@yahoo.com","0987654321");


		userService.registerUser(UserMapper.userToUserDTO(user1));

		userService.registerUser(UserMapper.userToUserDTO(user2));


	}
}
