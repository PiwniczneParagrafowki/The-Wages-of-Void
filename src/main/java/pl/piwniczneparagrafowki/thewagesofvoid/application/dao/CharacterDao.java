package pl.piwniczneparagrafowki.thewagesofvoid.application.dao;

import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public interface CharacterDao {

    void add(Character character);
    void delete(Character character);
    void update(Character character);
    Character get(int id);
    List<Character> getAll();
    
}
