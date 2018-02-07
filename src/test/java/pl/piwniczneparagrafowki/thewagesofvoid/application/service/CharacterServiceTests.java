package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.CharacterRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class CharacterServiceTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    CharacterRepository characterRepository;

    @Mock
    Character character;

    @InjectMocks
    CharacterServiceImpl characterService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCharacterAndReturnIt(){
        when(characterRepository.save(any(Character.class))).thenReturn(character);

        assertEquals(character, characterService.create(character));
    }

    @Test
    public void testCreateCharacterAndExpectDuplicateKeyException(){
        when(characterRepository.save(any(Character.class))).thenReturn(character);
        when(characterRepository.findById(anyLong())).thenReturn(character);

        exception.expect(DuplicateKeyException.class);
        characterService.create(character);
    }

    @Test
    public void testGetAllCharacters(){
        List<Character> characters;
        characters = Arrays.asList(character, character, character);
        when(characterRepository.findAll()).thenReturn(characters);

        assertEquals(characters, characterService.getAll());
        assertTrue(characterService.getAll().size()==3);
    }

    @Test
    public void testGetCharacter(){
        when(characterRepository.findById(anyLong())).thenReturn(character);

        assertEquals(characterService.get(anyLong()), character);
    }

    @Test
    public void testGetCharacterAndExpectEmptyResultDataAccessException(){
        when(characterRepository.findById(anyLong())).thenReturn(null);

        exception.expect(EmptyResultDataAccessException.class);
        characterService.get(anyLong());
    }

    @Test
    public void testUpdateCharacter(){
        when(characterRepository.findById(anyLong())).thenReturn(character);
        when(characterRepository.save(any(Character.class))).thenReturn(character);

        assertEquals(character, characterService.update(character));
    }

    @Test
    public void testUpdateCharacterAndExpectEmptyResultDataAccessException(){
        when(characterRepository.findById(anyLong())).thenReturn(null);
        when(characterRepository.save(any(Character.class))).thenReturn(character);

        exception.expect(EmptyResultDataAccessException.class);
        characterService.update(character);
    }

    @Test
    public void testDeleteCharacter(){
        when(characterRepository.findById(anyLong())).thenReturn(character);

        characterService.delete(character);
        verify(characterRepository, times(1)).delete(character);
    }

    @Test
    public void testDeleteCharacterAndExpectEmptyResultDataAccessException(){
        when(characterRepository.findById(anyLong())).thenReturn(null);
        doNothing().when(characterRepository).delete(any(Character.class));

        exception.expect(EmptyResultDataAccessException.class);
        characterService.delete(character);
    }

}
