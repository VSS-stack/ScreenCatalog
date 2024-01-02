package com.example.screencatalog;

import com.example.screencatalog.service.ApiConsumption;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreencatalogApplication implements CommandLineRunner { //Interface to run application in command line

	public static void main(String[] args) {
		SpringApplication.run(ScreencatalogApplication.class, args); //call run method
	}

	@Override
	public void run(String... args) throws Exception { //Implements run method
		var apiConsumption = new ApiConsumption();
		var json = apiConsumption.getData("http://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=306f71f9");
		System.out.println(json);
	}
}
