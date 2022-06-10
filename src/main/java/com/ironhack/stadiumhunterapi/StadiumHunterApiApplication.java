package com.ironhack.stadiumhunterapi;

import com.ironhack.stadiumhunterapi.model.Stadium;
import com.ironhack.stadiumhunterapi.model.User;
import com.ironhack.stadiumhunterapi.service.impl.ReviewService;
import com.ironhack.stadiumhunterapi.service.impl.StadiumService;
import com.ironhack.stadiumhunterapi.service.impl.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;

@SpringBootApplication
@EnableSwagger2
public class StadiumHunterApiApplication {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:3000");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(StadiumHunterApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(StadiumService stadiumService, ReviewService reviewService, UserService userService) {
		return args -> {
			/*stadiumService.saveStadium(new Stadium(1L,"La Bombonera", "Buenos Aires","Argentina",50000,-34.635497,-58.364613));
			stadiumService.saveStadium(new Stadium(2L,"La Bombonera", "Buenos Aires","Argentina",50000,-34.635497,-58.364613));
			stadiumService.saveStadium(new Stadium(3L,"Camp Nou", "Barcelona","España",50000,41.381339,2.122745));*/

			userService.saveUser(new User("Pilar","pilar@gmail.com", "P@ssw0rd"));


		};
	}

}
