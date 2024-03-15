package br.com.maxdev.screenmatch.models;

import java.time.LocalDate;
import java.lang.NullPointerException;
import java.time.format.DateTimeParseException;

public class Episode {
    String title;
    Double rating;
    Integer episode;
    Integer season;
    LocalDate dataReleased;

    public Episode(String title, String rating, int episode, int season, String dataReleased) {
        this.title = title;
        this.episode = episode;
        this.season = season;
        try {
            this.rating = Double.parseDouble(rating);
            this.dataReleased = LocalDate.parse(dataReleased);
        } catch (NullPointerException | NumberFormatException e) {
            this.rating = 0.0;
        } catch (DateTimeParseException e) {
            this.dataReleased = null;
        }
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Title= %s, rating= %s, episode= %d, dataReleased= %s".formatted(this.title, this.rating, this.episode, this.dataReleased);
    }
}
