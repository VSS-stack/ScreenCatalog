package com.example.screencatalog.model;

import com.fasterxml.jackson.annotation.JsonAlias;

/* Using JsonAlias to indicate the corresponding attribute in the json */
public record SerieData(@JsonAlias("Title") String title,
                        @JsonAlias("totalSeasons") Integer totalSeasons,
                        @JsonAlias("imdbRating") String rating) {
}
