package com.example.screencatalog.principal;

import com.example.screencatalog.model.EpisodeData;
import com.example.screencatalog.model.SeasonData;
import com.example.screencatalog.model.SerieData;
import com.example.screencatalog.service.ApiConsumption;
import com.example.screencatalog.service.ConvertData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Class that show the menu */
public class Principal {
    private Scanner input = new Scanner(System.in);
    private ApiConsumption apiConsumption = new ApiConsumption(); //object to consume the API
    private ConvertData converter = new ConvertData(); //object to convert string to object
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=306f71f9";

    /* Method to show the menu */
    public void showMenu() {
        System.out.println("Write the name of the tv serie:");
        var serieName = input.nextLine().replace(" ", "+");

        /* Make a request and return a string */
        var json = apiConsumption.getData(ADDRESS + serieName + API_KEY);

        /* Convert a string into a SerieData object */
        SerieData newSerie = converter.getData(json, SerieData.class);

        System.out.println(newSerie);

        /* Create an Arraylist of seasons */
		List<SeasonData> seasons = new ArrayList<>();

		/* Iterate through the seasons */
		for (int i = 1; i <= newSerie.totalSeasons(); i++) {
			/* Make a new request with the season and return a string */
			json = apiConsumption.getData(ADDRESS + serieName + "&season=" + i + API_KEY);

			/* Convert a string into a SeasonData object */
			SeasonData newSeason = converter.getData(json, SeasonData.class);

			/* Add to Arraylist */
			seasons.add(newSeason);
		}

		/* Print the seasons */
		seasons.forEach(System.out::println);

        /* Print only the title of each episode with lambda expressions */
        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));
    }
}
