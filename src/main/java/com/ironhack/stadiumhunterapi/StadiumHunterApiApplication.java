package com.ironhack.stadiumhunterapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.stadiumhunterapi.model.Stadium;
import com.ironhack.stadiumhunterapi.model.User;
import com.ironhack.stadiumhunterapi.service.impl.ReviewService;
import com.ironhack.stadiumhunterapi.service.impl.StadiumService;
import com.ironhack.stadiumhunterapi.service.impl.UserService;
import org.jboss.jandex.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
			userService.saveUser(new User("Pilar","pilar@gmail.com", "P@ssw0rd"));

			ObjectMapper mapper = new ObjectMapper();
			//InputStream inputStream = new FileInputStream(new File("../../resources/json/stadiums.json"));
			//TypeReference<List<Stadium>> typeReference = new TypeReference<List<Stadium>>(){};
			//List<Stadium> stadiums = mapper.readValue(inputStream, typeReference);
			URL resource = Main.class.getClassLoader().getResource("stadiums.json");
			byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
			String json = new String(bytes);

			JsonNode node = mapper.readTree(json);
			if(node.isArray()){
				Long id = 1L;
				for(JsonNode jsonNode : node){
					String name = jsonNode.get("Name").asText();
					String city = jsonNode.get("Town").asText();
					String country = jsonNode.get("Nation").asText();
					int capacity = 0;
					try{
						capacity = Integer.parseInt(jsonNode.get("Capacity").asText().replace(".",""));
					} catch(Exception e){
						capacity = 0;
					}
					double latitude = jsonNode.get("Latitude").asDouble();
					double longitude = jsonNode.get("Longitude").asDouble();
					Stadium stadium = new Stadium(id,name,city, country, capacity, latitude, longitude);
					stadiumService.saveStadium(stadium);
					id+=1;
				}
			}
		};
	}

}
