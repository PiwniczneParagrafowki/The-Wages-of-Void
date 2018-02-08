package pl.piwniczneparagrafowki.thewagesofvoid.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
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

    Item findByNameAndCharacter(String name, Character character);

    List<Item> findByCharacter(Character character);

    List<Item> findAll();

    void delete(Item item);
    
}
