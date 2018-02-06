package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.CharacterRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    @Resource
    CharacterRepository characterRepository;

    @Override
    public Character create(Character character) {
        if(characterRepository.findById(character.getId())==null) {
            characterRepository.save(character);
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
            throw new EmptyResultDataAccessException(1);
        }
        return character;
    }

    @Override
    public Character get(long id) {
        return characterRepository.findById(id);
    }

    @Override
    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    @Override
    public void delete(Character character) {
        characterRepository.delete(character);
    }
}
