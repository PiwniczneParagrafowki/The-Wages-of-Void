package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.CharacterRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * @Author arkadiusz.parafiniuk@gmail.com
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    @Resource
    CharacterRepository characterRepository;

    @Override
    public Character create(Character character) {
        if(characterRepository.findById(character.getId())==null) {
            characterRepository.save(character);
        } else {
            throw new DuplicateKeyException("CREATE FAILED: Object with id=" + character.getId() + " already exist in the database.");
        }
        return character;
    }

    @Override
    public Character update(Character character) {
        Character oldCharacter;
        oldCharacter = characterRepository.findById(character.getId());
        if(oldCharacter!=null) {
            characterRepository.save(character);
        } else {
            throw new EmptyResultDataAccessException("UPDATE FAILED: Character with id=" + character.getId() + " not found in the database.", 1);
        }
        return character;
    }

    @Override
    public Character get(long id) {
        Character character;
        character = characterRepository.findById(id);
        if(character==null) {
            throw new EmptyResultDataAccessException("GET FAILED: Character with id=" + id + " not found in the database.", 1);
        }
        return character;
    }

    @Override
    public List<Character> getAll() {
        List<Character> characters = new ArrayList<>();
        characters = characterRepository.findAll();
        if(characters.isEmpty()) {
            throw new EmptyResultDataAccessException("GET ALL FAILED: No characters found in the database.", 1);
        }
        return characters;
    }

    @Override
    public void delete(Character character) {
        Character tmpCharacter;
        tmpCharacter = characterRepository.findById(character.getId());
        if(tmpCharacter!=null){
            characterRepository.delete(character);
        } else {
            throw new EmptyResultDataAccessException("GET FAILED: Character with id=" + character.getId() + " not found in the database.", 1);
        }
    }
}
