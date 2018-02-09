package pl.piwniczneparagrafowki.thewagesofvoid.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;

import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 *
 * @Author arkadiusz.parafiniuk@gmail.com
 */
@Repository
public interface HeroRepository extends CrudRepository<Hero, Long> {

    Hero save(Hero hero);

    Hero findById(long id);

    List<Hero> findAll();

    void delete(Hero hero);

}
