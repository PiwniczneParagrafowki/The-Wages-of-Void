package pl.piwniczneparagrafowki.thewagesofvoid.application.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piwniczneparagrafowki.thewagesofvoid.application.StartController;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.CharacterService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@RestController
@RequestMapping("/api")
public class CharacterController {

    private static final Log LOG = LogFactory.getLog(CharacterController.class);

    @Resource
    CharacterService characterService;

    @RequestMapping(value = "/character/", method = RequestMethod.GET)
    public ResponseEntity<List<Character>> getAllCharacters() {
        LOG.info("GET /api/character/");
        List<Character> characters;
        characters = characterService.getAll();
        return new ResponseEntity<List<Character>>(characters, HttpStatus.OK);
    }

    @RequestMapping(value = "/character/{id}", method = RequestMethod.GET)
    public ResponseEntity<Character> getCharacter(@PathVariable("id") long id) {
        LOG.info("GET /api/character/{id} id:"+id);
        Character character;
        try {
            character = characterService.get(id);
        } catch (EmptyResultDataAccessException e) {
            LOG.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Character>(character, HttpStatus.OK);
    }

    @RequestMapping(value = "/character/", method = RequestMethod.POST)
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        LOG.info("POST /character/ character:" + character.getName());
        try {
            characterService.create(character);
        } catch (DuplicateKeyException e) {
            LOG.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Character>(character, HttpStatus.OK);
    }

    @RequestMapping(value = "/character/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Character> updateCharacter(@PathVariable("id") long id, @RequestBody Character character) {
        LOG.info("PUT /character/{id} id:"+id);
        if(character.getId()!=id) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            characterService.update(character);
        } catch (EmptyResultDataAccessException e) {
            LOG.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Character>(character, HttpStatus.OK);
    }

    @RequestMapping(value = "/character/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@RequestBody Character character) {
        LOG.info("DELETE /character/ charatcer:" + character.getName());
        characterService.delete(character);
        return new ResponseEntity(HttpStatus.OK);
    }

}
