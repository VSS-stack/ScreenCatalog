package com.example.screencatalog;

import com.example.screencatalog.model.EpisodeData;
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
		/* Create API consumption object */
		var apiConsumption = new ApiConsumption();

		/* Make a request and return a string */
		var json = apiConsumption.getData("http://www.omdbapi.com/?t=gilmore+girls&apikey=306f71f9");
		System.out.println(json);

		/* Create converter string to object */
		ConvertData converter = new ConvertData();

		/* Convert a string into a SerieData object */
		SerieData newSerie = converter.getData(json, SerieData.class);
		System.out.println(newSerie);

		/* Convert a string into a EpisodeData object */
		json = apiConsumption.getData("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=306f71f9");
		EpisodeData newEpisode = converter.getData(json, EpisodeData.class);
		System.out.println(newEpisode);
	}
}
