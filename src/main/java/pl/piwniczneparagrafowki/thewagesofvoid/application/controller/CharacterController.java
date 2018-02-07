package pl.piwniczneparagrafowki.thewagesofvoid.application.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.CharacterService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * @Author arkadiusz.parafiniuk@gmail.com
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
        if(characters.isEmpty()) return new ResponseEntity<>(characters, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @RequestMapping(value = "/character/{id}", method = RequestMethod.GET)
    public ResponseEntity<Character> getCharacter(@PathVariable("id") long id) {
        LOG.info("GET /api/character/{id} id:"+id);
        Character character = characterService.get(id);
        return new ResponseEntity<Character>(character, HttpStatus.OK);
    }

    @RequestMapping(value = "/character/", method = RequestMethod.POST)
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        LOG.info("POST /character/ character:" + character.getName());
        characterService.create(character);
        return new ResponseEntity<Character>(character, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/character/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Character> updateCharacter(@PathVariable("id") long id, @RequestBody Character character) {
        LOG.info("PUT /character/{id} id:"+id);
        if(character.getId()!=id) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        characterService.update(character);
        return new ResponseEntity<Character>(character, HttpStatus.OK);
    }

    @RequestMapping(value = "/character/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCharacter(@RequestBody Character character) {
        LOG.info("DELETE /character/ character:" + character.getName());
        characterService.delete(character);
        return new ResponseEntity(HttpStatus.OK);
    }

}
