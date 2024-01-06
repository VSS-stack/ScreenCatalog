package com.example.screencatalog.principal;

import com.example.screencatalog.service.ApiConsumption;

import java.util.Scanner;

/* Class that show the menu */
public class Principal {
    private Scanner input = new Scanner(System.in);
    private ApiConsumption apiConsumption = new ApiConsumption(); //object to consume the API
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=306f71f9";

    /* Method to show the menu */
    public void showMenu() {
        System.out.println("Write the name of the tv serie:");
        var serieName = input.nextLine().replace(" ", "+");

        /* Make a request and return a string */
        var json = apiConsumption.getData(ADDRESS + serieName + API_KEY);
    }
}
