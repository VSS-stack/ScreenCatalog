package com.example.screencatalog.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Record that represents a episode */
@JsonIgnoreProperties(ignoreUnknown = true) //ignore attributes not mapped
/* Using JsonAlias to indicate the corresponding attribute in the json */
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer episodeNumber,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("Released") String releasingDate) {
}
