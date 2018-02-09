package pl.piwniczneparagrafowki.thewagesofvoid.application.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piwniczneparagrafowki.thewagesofvoid.application.GlobalControllerExceptionHandler;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.HeroService;
import pl.piwniczneparagrafowki.thewagesofvoid.application.tools.JsonConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class HeroControllerTests {

    @Mock
    HeroService heroService;

    @InjectMocks
    HeroController heroController;

    private MockMvc mockMvc;

    Hero hero = new Hero(1, "Tom", 99, new User());
    Hero hero2 = new Hero(2, "Jerry", 99, new User());
    List<Hero> heroes = Arrays.asList(hero, hero2);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(heroController)
                .setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetCharacterAndExpectStatusOk() throws Exception {
        when(heroService.get(1)).thenReturn(hero);

        this.mockMvc.perform(get("/api/hero/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tom"));
    }

    @Test
    public void testGetCharacterAndExpectStatusNotFound() throws Exception {
        when(heroService.get(anyLong())).thenThrow(EmptyResultDataAccessException.class);

        this.mockMvc.perform(get("/api/hero/"+anyLong()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllCharactersAndExpectStatusOk() throws Exception {
        when(heroService.getAll()).thenReturn(heroes);

        this.mockMvc.perform(get("/api/hero/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(hero.getId()))
                .andExpect(jsonPath("$.[0].name").value(hero.getName()))
                .andExpect(jsonPath("$.[0].health").value(hero.getHp()))
                .andExpect(jsonPath("$.[1].id").value(hero2.getId()))
                .andExpect(jsonPath("$.[1].name").value(hero2.getName()))
                .andExpect(jsonPath("$.[1].health").value(hero2.getHp()));
    }

    @Test
    public void testGetAllCharactersAndExpectStatusNotFound() throws Exception {
        when(heroService.getAll()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/api/hero/"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testCreateCharacterAndExpectStatusCreated() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(hero);
        when(heroService.create(hero)).thenReturn(hero);

        mockMvc.perform(post("/api/hero/").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateCharacterAndExpectStatusConflict() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(hero);
        when(heroService.create(any(Hero.class))).thenThrow(DuplicateKeyException.class);

        mockMvc.perform(post("/api/hero/").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdateCharacterAndExpectStatusOk() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(hero);
        when(heroService.update(hero)).thenReturn(hero);

        mockMvc.perform(put("/api/hero/"+ hero.getId()).contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCharacterAndExpectStatusNotFound() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(hero);
        when(heroService.update(any(Hero.class))).thenThrow(EmptyResultDataAccessException.class);

        mockMvc.perform(put("/api/hero/1").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCharacterAndExpectStatusBadRequest() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(hero);

        mockMvc.perform(put("/api/hero/2").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteCharacterAndExpectStatusOk() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(hero);
        doNothing().when(heroService).delete(hero);

        mockMvc.perform(delete("/api/hero/").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCharacterAndExpectStatusNotFound() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(hero);
        doThrow(EmptyResultDataAccessException.class).when(heroService).delete(any(Hero.class));

        mockMvc.perform(delete("/api/hero/").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isNotFound());
    }

}
