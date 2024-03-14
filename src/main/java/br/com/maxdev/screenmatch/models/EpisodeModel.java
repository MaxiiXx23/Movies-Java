package br.com.maxdev.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeModel(@JsonAlias("Title") String title,
                           @JsonAlias("Season") Integer season,
                           @JsonAlias("Episode") Integer episode,
                           @JsonAlias("Ratings") ArrayList rating,
                           @JsonAlias("Released") String dataReleased,
                           @JsonAlias("Runtime") String duration) {
}
