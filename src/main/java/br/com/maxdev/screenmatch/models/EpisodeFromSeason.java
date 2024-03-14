package br.com.maxdev.screenmatch.models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeFromSeason(@JsonAlias("Title") String title,
                                 @JsonAlias("imdbRating") String rating,
                                 @JsonAlias("Episode") Integer episode) {
}
