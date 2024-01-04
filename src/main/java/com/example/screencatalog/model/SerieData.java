package com.example.screencatalog.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //To ignore attributes not mapped
/* Using JsonAlias to indicate the corresponding attribute in the json */
public record SerieData(@JsonAlias("Title") String title,
                        @JsonAlias("totalSeasons") Integer totalSeasons,
                        @JsonAlias("imdbRating") String rating) {
}
