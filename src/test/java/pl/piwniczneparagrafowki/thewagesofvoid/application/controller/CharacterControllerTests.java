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
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.CharacterService;
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
public class CharacterControllerTests {

    @Mock
    CharacterService characterService;

    @InjectMocks
    CharacterController characterController;

    private MockMvc mockMvc;

    Character character = new Character(1, "Tom", 99, new User());
    Character character2 = new Character(2, "Jerry", 99, new User());
    List<Character> characters = Arrays.asList(character, character2);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(characterController)
                .setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetCharacterAndExpectStatusOk() throws Exception {
        when(characterService.get(1)).thenReturn(character);

        this.mockMvc.perform(get("/api/character/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tom"));
    }

    @Test
    public void testGetCharacterAndExpectStatusNotFound() throws Exception {
        when(characterService.get(anyLong())).thenThrow(EmptyResultDataAccessException.class);

        this.mockMvc.perform(get("/api/character/"+anyLong()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllCharactersAndExpectStatusOk() throws Exception {
        when(characterService.getAll()).thenReturn(characters);

        this.mockMvc.perform(get("/api/character/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(character.getId()))
                .andExpect(jsonPath("$.[0].name").value(character.getName()))
                .andExpect(jsonPath("$.[0].health").value(character.getHealth()))
                .andExpect(jsonPath("$.[1].id").value(character2.getId()))
                .andExpect(jsonPath("$.[1].name").value(character2.getName()))
                .andExpect(jsonPath("$.[1].health").value(character2.getHealth()));
    }

    @Test
    public void testGetAllCharactersAndExpectStatusNotFound() throws Exception {
        when(characterService.getAll()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/api/character/"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testCreateCharacterAndExpectStatusCreated() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(character);
        when(characterService.create(character)).thenReturn(character);

        mockMvc.perform(post("/api/character/").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateCharacterAndExpectStatusConflict() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(character);
        when(characterService.create(any(Character.class))).thenThrow(DuplicateKeyException.class);

        mockMvc.perform(post("/api/character/").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdateCharacterAndExpectStatusOk() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(character);
        when(characterService.update(character)).thenReturn(character);

        mockMvc.perform(put("/api/character/"+character.getId()).contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCharacterAndExpectStatusNotFound() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(character);
        when(characterService.update(any(Character.class))).thenThrow(EmptyResultDataAccessException.class);

        mockMvc.perform(put("/api/character/1").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCharacterAndExpectStatusBadRequest() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(character);

        mockMvc.perform(put("/api/character/2").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteCharacterAndExpectStatusOk() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(character);
        doNothing().when(characterService).delete(character);

        mockMvc.perform(delete("/api/character/").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCharacterAndExpectStatusNotFound() throws Exception {
        String jsonCharacter = JsonConverter.getInstance().toJson(character);
        doThrow(EmptyResultDataAccessException.class).when(characterService).delete(any(Character.class));

        mockMvc.perform(delete("/api/character/").contentType(MediaType.APPLICATION_JSON).content(jsonCharacter))
                .andExpect(status().isNotFound());
    }

}
