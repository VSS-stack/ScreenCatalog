package com.example.screencatalog.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/* Record that represents a season */
@JsonIgnoreProperties(ignoreUnknown = true) //ignore attributes not mapped
/* Using JsonAlias to indicate the corresponding attribute in the json */
public record SeasonData(@JsonAlias("Season") Integer seasonNumber,
                         @JsonAlias("Episodes") List<EpisodeData> episodes) {
}
