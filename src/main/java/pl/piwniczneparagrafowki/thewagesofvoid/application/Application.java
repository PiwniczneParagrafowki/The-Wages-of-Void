package pl.piwniczneparagrafowki.thewagesofvoid.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.CharacterRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.CharacterService;

import javax.annotation.Resource;

@SpringBootApplication
public class Application {

	private static final Log LOG = LogFactory.getLog(Application.class);

	@Resource
	CharacterService characterService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			LOG.info("Application 'The Wages of Void' started");

			Character character = new Character();
			character.setName("Andrzej - Wojownik o dwóch twarzach");
			character.setHealth(99);
			characterService.create(character);

			System.out.println(characterService.get(1));

			character.setId(2);

			characterService.delete(character);

		};
	}

}
