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
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.UserService;
import pl.piwniczneparagrafowki.thewagesofvoid.application.tools.JsonConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class UserControllerTests {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    private MockMvc mockMvc;

    User user = new User(1, "Tom", "testPassword", "test@email.com");
    User user2 = new User(2, "Jerry", "testPassword", "test@email.com");
    List<User> users = Arrays.asList(user, user2);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetUserAndExpectStatusOk() throws Exception {
        when(userService.get(1)).thenReturn(user);

        this.mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tom"));
    }

    @Test
    public void testGetUserAndExpectStatusNotFound() throws Exception {
        when(userService.get(anyLong())).thenThrow(EmptyResultDataAccessException.class);

        this.mockMvc.perform(get("/api/user/"+anyLong()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllUsersAndExpectStatusOk() throws Exception {
        when(userService.getAll()).thenReturn(users);

        this.mockMvc.perform(get("/api/user/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(user.getId()))
                .andExpect(jsonPath("$.[0].name").value(user.getName()))
                .andExpect(jsonPath("$.[1].id").value(user2.getId()))
                .andExpect(jsonPath("$.[1].name").value(user2.getName()));
    }

    @Test
    public void testGetAllUsersAndExpectStatusNotFound() throws Exception {
        when(userService.getAll()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/api/user/"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testCreateUserAndExpectStatusCreated() throws Exception {
        String jsonUser = JsonConverter.getInstance().toJson(user);
        when(userService.create(user)).thenReturn(user);

        mockMvc.perform(post("/api/user/").contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateUserAndExpectStatusConflict() throws Exception {
        String jsonUser = JsonConverter.getInstance().toJson(user);
        when(userService.create(any(User.class))).thenThrow(DuplicateKeyException.class);

        mockMvc.perform(post("/api/user/").contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdateUserAndExpectStatusOk() throws Exception {
        String jsonUser = JsonConverter.getInstance().toJson(user);
        when(userService.update(user)).thenReturn(user);

        mockMvc.perform(put("/api/user/"+user.getId()).contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserAndExpectStatusNotFound() throws Exception {
        String jsonUser = JsonConverter.getInstance().toJson(user);
        when(userService.update(any(User.class))).thenThrow(EmptyResultDataAccessException.class);

        mockMvc.perform(put("/api/user/1").contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUserAndExpectStatusBadRequest() throws Exception {
        String jsonUser = JsonConverter.getInstance().toJson(user);

        mockMvc.perform(put("/api/user/2").contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteUserAndExpectStatusOk() throws Exception {
        String jsonUser = JsonConverter.getInstance().toJson(user);
        doNothing().when(userService).delete(user);

        mockMvc.perform(delete("/api/user/").contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserAndExpectStatusNotFound() throws Exception {
        String jsonUser = JsonConverter.getInstance().toJson(user);
        doThrow(EmptyResultDataAccessException.class).when(userService).delete(any(User.class));

        mockMvc.perform(delete("/api/user/").contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(status().isNotFound());
    }
    
}
