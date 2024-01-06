package com.example.screencatalog;

import com.example.screencatalog.model.EpisodeData;
import com.example.screencatalog.model.SeasonData;
import com.example.screencatalog.model.SerieData;
import com.example.screencatalog.service.ApiConsumption;
import com.example.screencatalog.service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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

		/* Make a new request and return a string */
		json = apiConsumption.getData("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=306f71f9");

		/* Convert a string into a EpisodeData object */
		EpisodeData newEpisode = converter.getData(json, EpisodeData.class);
		System.out.println(newEpisode);

		/* Create an Arraylist of seasons */
		List<SeasonData> seasons = new ArrayList<>();

		/* Iterate through the seasons */
		for (int i = 1; i <= newSerie.totalSeasons(); i++) {
			/* Make a new request and return a string */
			json = apiConsumption.getData("https://www.omdbapi.com/?t=gilmore+girls&season=" + i +
					"&apikey=306f71f9");

			/* Convert a string into a SeasonData object */
			SeasonData newSeason = converter.getData(json, SeasonData.class);

			/* Add to Arraylist */
			seasons.add(newSeason);
		}

		/* Print the seasons */
		seasons.forEach(System.out::println);
	}
}
