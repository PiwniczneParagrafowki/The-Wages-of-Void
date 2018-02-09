package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;

import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * @Author arkadiusz.parafiniuk@gmail.com
 */
public interface HeroService {

    Hero create(Hero hero);

    Hero update(Hero hero);

    Hero get(long id);

    List<Hero> getAll();

    void delete(Hero hero);

}
