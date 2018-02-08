package pl.piwniczneparagrafowki.thewagesofvoid.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Item;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.CharacterRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.ItemRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.UserRepository;

import javax.annotation.Resource;

@SpringBootApplication
public class Application {

	private static final Log LOG = LogFactory.getLog(Application.class);

	@Resource
	CharacterRepository characterRepository;

	@Resource
	ItemRepository itemRepository;

	@Resource
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			LOG.info("Application 'The Wages of Void' started");

			User user = userRepository.save(new User("user123", "password", "test@fsd.pl"));

			Character character = characterRepository.save(new Character("Tom", 99, user));
			Character character2 = characterRepository.save(new Character("Jerry", 99, user));

			Item item = itemRepository.save(new Item("Coś", 3, character));
			Item item2 = itemRepository.save(new Item("Coś innego", 1, character));
			Item item3 = itemRepository.save(new Item("Coś kogoś innego", 3, character2));

			Character testCharacter = character;
			testCharacter.setName("Darwin");
			testCharacter.setHp(12);

			System.out.println(itemRepository.findByNameAndCharacter("Coś", testCharacter));

		};
	}

}
