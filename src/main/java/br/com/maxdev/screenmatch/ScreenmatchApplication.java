package br.com.maxdev.screenmatch;

import br.com.maxdev.screenmatch.models.SerieModel;
import br.com.maxdev.screenmatch.service.ConsumeAPI;
import br.com.maxdev.screenmatch.service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumeAPI consumeAPI = new ConsumeAPI();
		ConvertData conversor = new ConvertData();
		String json = consumeAPI.getData("https://www.omdbapi.com/?t=dune&apikey=775de322");

		SerieModel serie = conversor.getData(json, SerieModel.class);

		System.out.println(serie);
	}
}
