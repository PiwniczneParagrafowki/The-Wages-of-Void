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
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.HeroRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class HeroServiceTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    HeroRepository heroRepository;

    @Mock
    Hero hero;

    @InjectMocks
    HeroServiceImpl characterService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCharacterAndReturnIt(){
        when(heroRepository.save(any(Hero.class))).thenReturn(hero);

        assertEquals(hero, characterService.create(hero));
    }

    @Test
    public void testCreateCharacterAndExpectDuplicateKeyException(){
        when(heroRepository.save(any(Hero.class))).thenReturn(hero);
        when(heroRepository.findById(anyLong())).thenReturn(hero);

        exception.expect(DuplicateKeyException.class);
        characterService.create(hero);
    }

    @Test
    public void testGetAllCharacters(){
        List<Hero> heroes;
        heroes = Arrays.asList(hero, hero, hero);
        when(heroRepository.findAll()).thenReturn(heroes);

        assertEquals(heroes, characterService.getAll());
        assertTrue(characterService.getAll().size()==3);
    }

    @Test
    public void testGetCharacter(){
        when(heroRepository.findById(anyLong())).thenReturn(hero);

        assertEquals(characterService.get(anyLong()), hero);
    }

    @Test
    public void testGetCharacterAndExpectEmptyResultDataAccessException(){
        when(heroRepository.findById(anyLong())).thenReturn(null);

        exception.expect(EmptyResultDataAccessException.class);
        characterService.get(anyLong());
    }

    @Test
    public void testUpdateCharacter(){
        when(heroRepository.findById(anyLong())).thenReturn(hero);
        when(heroRepository.save(any(Hero.class))).thenReturn(hero);

        assertEquals(hero, characterService.update(hero));
    }

    @Test
    public void testUpdateCharacterAndExpectEmptyResultDataAccessException(){
        when(heroRepository.findById(anyLong())).thenReturn(null);
        when(heroRepository.save(any(Hero.class))).thenReturn(hero);

        exception.expect(EmptyResultDataAccessException.class);
        characterService.update(hero);
    }

    @Test
    public void testDeleteCharacter(){
        when(heroRepository.findById(anyLong())).thenReturn(hero);

        characterService.delete(hero);
        verify(heroRepository, times(1)).delete(hero);
    }

    @Test
    public void testDeleteCharacterAndExpectEmptyResultDataAccessException(){
        when(heroRepository.findById(anyLong())).thenReturn(null);
        doNothing().when(heroRepository).delete(any(Hero.class));

        exception.expect(EmptyResultDataAccessException.class);
        characterService.delete(hero);
    }

}
