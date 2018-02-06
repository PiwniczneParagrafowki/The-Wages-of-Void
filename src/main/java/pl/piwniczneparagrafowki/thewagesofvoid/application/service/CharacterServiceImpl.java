package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

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
    public Character save(Character character) {
        return characterRepository.save(character);
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
