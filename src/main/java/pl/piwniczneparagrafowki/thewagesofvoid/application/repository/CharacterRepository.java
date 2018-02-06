package pl.piwniczneparagrafowki.thewagesofvoid.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;

import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 *
 * @Author arkadiusz.parafiniuk@gmail.com
 */
@Repository
public interface CharacterRepository extends CrudRepository<Character, Long> {

    Character save(Character character);

    Character findById(long id);

    List<Character> findAll();

    void delete(Character character);

}
