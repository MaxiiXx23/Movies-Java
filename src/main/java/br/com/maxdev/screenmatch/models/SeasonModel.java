package br.com.maxdev.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonModel(@JsonAlias("Title") String title,
                          @JsonAlias("Season") Integer season,
                          @JsonAlias("totalSeasons") String totalSeasons,
                          @JsonAlias("Episodes") ArrayList<EpisodeFromSeason> episodes) {
}
