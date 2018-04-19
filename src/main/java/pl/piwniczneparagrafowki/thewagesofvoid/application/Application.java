package pl.piwniczneparagrafowki.thewagesofvoid.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.HeroRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.ItemRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.ParagraphRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.UserRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.ParagraphService;

import javax.annotation.Resource;

@SpringBootApplication
public class Application {

	private static final Log LOG = LogFactory.getLog(Application.class);

	@Resource
	HeroRepository heroRepository;

	@Resource
	ItemRepository itemRepository;

	@Resource
	UserRepository userRepository;

	@Resource
	ParagraphRepository paragraphRepository;

	@Resource
	ParagraphService paragraphService;

	@Resource
	ParagraphsDataMigration paragraphsDataMigration;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			LOG.info("Application 'The Wages of Void' started");

			paragraphsDataMigration.migrate();

//			User user = userRepository.save(new User("user123", "password", "test@fsd.pl"));
//
//			Hero hero = heroRepository.save(new Hero("Tom", 99, user));
//			Hero hero2 = heroRepository.save(new Hero("Jerry", 99, user));
//
//			Item item = itemRepository.save(new Item("Coś", 3, hero));
//			Item item2 = itemRepository.save(new Item("Coś innego", 1, hero));
//			Item item3 = itemRepository.save(new Item("Coś kogoś innego", 3, hero2));

//			Hero testHero = hero;
//			testHero.setName("Darwin");
//			testHero.setHp(12);

//			System.out.println(itemRepository.findByNameAndHero("Coś", testHero));
//			System.out.println(itemRepository.findByNameAndHeroId("Coś", testHero.getId()));

//			Paragraph paragraph = new Paragraph();
//			paragraph.setContent("<h1>Hello World!</h1>");
//			paragraph.setId(2);
//			paragraphRepository.save(paragraph);
//
//			Paragraph paragraph1 = new Paragraph();
//			paragraph1.setContent("<a href=\"http://localhost:8080/api/paragraph/1\">Hello</a>");
//			paragraphRepository.save(paragraph1);

//			ParagraphsDataMigration paragraphsDataMigration = new ParagraphsDataMigration();
//			paragraphsDataMigration.migrate();

//			ParagraphServiceImpl paragraphService = new ParagraphServiceImpl();
//			paragraphService.save(1L, "test");


		};
	}

}
