package com.example.screencatalog.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate releasingDate;

    public Episode(Integer season, EpisodeData episode) {
        this.season = season;
        this.title = episode.title();
        this.episodeNumber = episode.episodeNumber();
        try {
            this.rating = Double.parseDouble(episode.rating());
        } catch (NumberFormatException e) {
            this.rating = 0.0;
        }
        try {
            this.releasingDate = LocalDate.parse(episode.releasingDate());
        } catch (DateTimeParseException e) {
            this.releasingDate = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleasingDate() {
        return releasingDate;
    }

    public void setReleasingDate(LocalDate releasingDate) {
        this.releasingDate = releasingDate;
    }

    @Override
    public String toString() {
        return  "season=" + season +
                ", title='" + title + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", rating=" + rating +
                ", releasingDate=" + releasingDate;
    }
}
