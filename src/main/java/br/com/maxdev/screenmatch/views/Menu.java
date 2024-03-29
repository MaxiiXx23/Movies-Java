package br.com.maxdev.screenmatch.views;

import br.com.maxdev.screenmatch.models.Episode;
import br.com.maxdev.screenmatch.models.EpisodeFromSeason;
import br.com.maxdev.screenmatch.models.SeasonModel;
import br.com.maxdev.screenmatch.models.SerieModel;
import br.com.maxdev.screenmatch.service.ConsumeAPI;
import br.com.maxdev.screenmatch.service.ConvertData;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu {
    ConsumeAPI consumeAPI = new ConsumeAPI();
    ConvertData conversor = new ConvertData();
    Scanner inputSearch = new Scanner(System.in);
    private final String BASE_URL = "https://www.omdbapi.com/?";
    private final String PARAM_TITLE = "t=";
    private final String PARAM_SEASON = "&season=";
    private final String API_KEY = "&apikey=ADICIONE SEU CODIGO AQUI";

    SerieModel serie;
    ArrayList<SeasonModel> listSeasons = new ArrayList<>();
    public void showMenu() {
        System.out.println("Pesquise sua série: ");
        var search = inputSearch.nextLine();
        var searchModified = search.replace(" ", "+").toLowerCase();
        String addressRequestSerie = BASE_URL + PARAM_TITLE + searchModified + API_KEY;
        String json = consumeAPI.getData(addressRequestSerie);

        this.serie = conversor.getData(json, SerieModel.class);

         // Now I'm requesting a season about a serie and adding in a ArrayList
		for(int i=1; i <= serie.totalSeasons(); i++) {
            String addressRequestSeason = BASE_URL + PARAM_TITLE + searchModified + PARAM_SEASON + i + API_KEY;
			json = consumeAPI.getData(addressRequestSeason);
			SeasonModel season = conversor.getData(json, SeasonModel.class);
			listSeasons.add(season);
		}

        List<Episode> episodes = listSeasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(e.title(), e.rating(), e.episode(), s.season(), e.dataReleased())))
                .collect(Collectors.toList());

        // episodes.forEach(System.out::println);

        // Média de avaliação por temporda
        Map<Integer, Double> averagePerSeason = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getRating)));

        System.out.println(averagePerSeason);

        // Estátisticas

        DoubleSummaryStatistics est = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getRating));

        System.out.println(est);

        /*

            // buscar por nome do episódio usando findFirst()

            System.out.println("Pesquise pelo nome do episódio:");
            var searchTitle = inputSearch.nextLine();

            Optional<Episode> episodeFound = episodes.stream()
                    .filter(e -> e.getTitle().toLowerCase().contains(searchTitle.toLowerCase()))
                    .findFirst();

            if(episodeFound.isPresent()){
                System.out.println("Episódio encontrado!");
                System.out.printf("Título: %s", episodeFound.get().getTitle());
            } else {
                System.out.println("Infelizmente não encontramos o episódio.");
            }


         */
        /*

                System.out.println("A partir de qual data deseja buscar os episódios?");
                int inputYearToSearch = this.inputSearch.nextInt();
                this.inputSearch.nextLine();

        LocalDate yearFormmated = LocalDate.of(inputYearToSearch, 1, 1);
            episodes.stream()
                .filter(e -> e.getDataReleased() != null && e.getDataReleased().isAfter(yearFormmated))
                .forEach(e -> {
                    System.out.printf("Título: %s Episódio: %d Data de lançamento: %s%n", e.getTitle(), e.getEpisode(), e.getDataReleased());
                });
         */

        /*
           List<EpisodeFromSeason> listEpisodes = listSeasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());
           Stream<EpisodeFromSeason> listSorted = listEpisodes.stream()
            .sorted(Comparator.comparing(EpisodeFromSeason::rating))
            .limit(5);

            listSorted.forEach(e -> System.out.println(e.title()));
        */

        /*

             // iteração com LAMBDA -> RECOMENDÁVEL

            // listSeasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

            // iteração de forma "manual" (Não é recomendada)
            for(int i = 0; i < serie.totalSeasons(); i++){
                ArrayList<EpisodeFromSeason> epsSeason = this.listSeasons.get(i).episodes();
                for(int j = 0; j< epsSeason.size(); j++){
                    System.out.println(epsSeason.get(j).title());
                }
            }
        */



    }
}
