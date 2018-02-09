package pl.piwniczneparagrafowki.thewagesofvoid.application.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.HeroService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * @Author arkadiusz.parafiniuk@gmail.com
 */
@RestController
@RequestMapping("/api")
public class HeroController {

    private static final Log LOG = LogFactory.getLog(HeroController.class);

    @Resource
    HeroService heroService;

    @RequestMapping(value = "/hero/", method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> getAllHeros() {
        LOG.info("GET /api/hero/");
        List<Hero> heroes;
        heroes = heroService.getAll();
        if(heroes.isEmpty()) return new ResponseEntity<>(heroes, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @RequestMapping(value = "/hero/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hero> getHero(@PathVariable("id") long id) {
        LOG.info("GET /api/hero/{id} id:"+id);
        Hero hero = heroService.get(id);
        return new ResponseEntity<Hero>(hero, HttpStatus.OK);
    }

    @RequestMapping(value = "/hero/", method = RequestMethod.POST)
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
        LOG.info("POST /hero/ hero:" + hero.getName());
        heroService.create(hero);
        return new ResponseEntity<Hero>(hero, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/hero/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Hero> updateHero(@PathVariable("id") long id, @RequestBody Hero hero) {
        LOG.info("PUT /hero/{id} id:"+id);
        if(hero.getId()!=id) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        heroService.update(hero);
        return new ResponseEntity<Hero>(hero, HttpStatus.OK);
    }

    @RequestMapping(value = "/hero/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteHero(@RequestBody Hero hero) {
        LOG.info("DELETE /hero/ hero:" + hero.getName());
        heroService.delete(hero);
        return new ResponseEntity(HttpStatus.OK);
    }

}
