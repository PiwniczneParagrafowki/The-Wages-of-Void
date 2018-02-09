package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.HeroRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * @Author arkadiusz.parafiniuk@gmail.com
 */
@Service
public class HeroServiceImpl implements HeroService {

    @Resource
    HeroRepository heroRepository;

    @Override
    public Hero create(Hero hero) {
        if(heroRepository.findById(hero.getId())==null) {
            heroRepository.save(hero);
        } else {
            throw new DuplicateKeyException("CREATE FAILED: Object with id=" + hero.getId() + " already exist in the database.");
        }
        return hero;
    }

    @Override
    public Hero update(Hero hero) {
        Hero oldHero;
        oldHero = heroRepository.findById(hero.getId());
        if(oldHero !=null) {
            heroRepository.save(hero);
        } else {
            throw new EmptyResultDataAccessException("UPDATE FAILED: Hero with id=" + hero.getId() + " not found in the database.", 1);
        }
        return hero;
    }

    @Override
    public Hero get(long id) {
        Hero hero;
        hero = heroRepository.findById(id);
        if(hero ==null) {
            throw new EmptyResultDataAccessException("GET FAILED: Hero with id=" + id + " not found in the database.", 1);
        }
        return hero;
    }

    @Override
    public List<Hero> getAll() {
        List<Hero> heroes = new ArrayList<>();
        heroes = heroRepository.findAll();
        return heroes;
    }

    @Override
    public void delete(Hero hero) {
        Hero tmpHero;
        tmpHero = heroRepository.findById(hero.getId());
        if(tmpHero !=null){
            heroRepository.delete(hero);
        } else {
            throw new EmptyResultDataAccessException("DELETE FAILED: Hero with id=" + hero.getId() + " not found in the database.", 1);
        }
    }
}
