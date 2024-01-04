package com.example.screencatalog;

import com.example.screencatalog.model.SerieData;
import com.example.screencatalog.service.ApiConsumption;
import com.example.screencatalog.service.ConvertData;
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
		var json = apiConsumption.getData("http://www.omdbapi.com/?t=gilmore+girls&apikey=306f71f9");
		System.out.println(json);

		ConvertData converter = new ConvertData();
		SerieData newData = converter.getData(json, SerieData.class);
		System.out.println(newData);
	}
}
