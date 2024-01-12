package com.example.screencatalog.principal;

import com.example.screencatalog.model.Episode;
import com.example.screencatalog.model.EpisodeData;
import com.example.screencatalog.model.SeasonData;
import com.example.screencatalog.model.SerieData;
import com.example.screencatalog.service.ApiConsumption;
import com.example.screencatalog.service.ConvertData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

//		/* Print the seasons */
//		seasons.forEach(System.out::println);
//
//        /* Print only the title of each episode with lambda expressions */
//        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

        /* Create a list with all the episodes */
        List<EpisodeData> episodeData = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());

        /* Show the top 10 episodes */
//        System.out.println("\nTop 10 episodes:");
//        episodeData.stream()
//                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("filter " + e))
//                .sorted(Comparator.comparing(EpisodeData::rating).reversed())
//                .peek(e -> System.out.println("sorted " + e))
//                .limit(10)
//                .peek(e -> System.out.println("limit " + e))
//                .map(e -> e.title().toUpperCase())
//                .peek(e -> System.out.println("map " + e))
//                .forEach(System.out::println);

        /* Create a list with episode objects */
        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(s.seasonNumber(), e)))
                .collect(Collectors.toList());

        episodes.forEach(System.out::println); //print the list

        /* Search episode from title */
        System.out.println("\nWrite full/part name of a episode:");
        var titlePart = input.nextLine(); //read the title part inputted by the user
        Optional<Episode> searchedEpisode = episodes.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(titlePart.toUpperCase()))
                .findFirst();//find the first occurrence of the title part
        if (searchedEpisode.isPresent()) { //test if searchedEpisode is not null
            System.out.println("Episode found!");
            System.out.println(searchedEpisode.get()); //print episode (toString)
        } else {
            System.out.println("Episode not found!");
        }

//        /* Filter episodes from a year */
//        System.out.println("\nFrom which year do you want to see the episodes?");
//        var startYear = input.nextInt();
//        input.nextLine(); //to handle the enter button
//        LocalDate searchDate = LocalDate.of(startYear, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodes.stream()
//                .filter(e -> e.getReleasingDate() != null && e.getReleasingDate().isAfter(searchDate))
//                .forEach(e -> System.out.println(
//                        "Season: " + e.getSeason() +
//                                " - Episode: " + e.getTitle() +
//                                " - Releasing date: " + e.getReleasingDate().format(formatter)
//                ));

        /* Show the average rating for the season */
        Map<Integer, Double> ratingBySeason = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
            .collect(Collectors.groupingBy(Episode::getSeason, Collectors.averagingDouble(Episode::getRating)));
        System.out.println(ratingBySeason);
    }
}
