package pl.piwniczneparagrafowki.thewagesofvoid.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Item;

import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    Item save(Item item);

    Item findById(long id);

    Item findByName(String name);

    Item findByNameAndHero(String name, Hero hero);

    Item findByNameAndHeroId(String name, long heroId);

    List<Item> findByHero(Hero hero);

    List<Item> findByHeroId(long heroId);

    List<Item> findAll();

    void delete(Item item);
    
}
