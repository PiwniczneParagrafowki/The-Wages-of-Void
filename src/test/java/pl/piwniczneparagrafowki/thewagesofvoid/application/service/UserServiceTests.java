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
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class UserServiceTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    UserRepository userRepository;

    @Mock
    User user;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCreateUserAndReturnIt(){
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(user.getPassword()).thenReturn("testPassword");

        assertEquals(user, userService.create(user));
    }

    @Test
    public void testCreateUserAndExpectDuplicateKeyException(){
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(anyLong())).thenReturn(user);
        when(user.getPassword()).thenReturn("testPassword");

        exception.expect(DuplicateKeyException.class);
        userService.create(user);
    }

    @Test
    public void testGetAllUsers(){
        List<User> users;
        users = Arrays.asList(user, user, user);
        when(userRepository.findAll()).thenReturn(users);

        assertEquals(users, userService.getAll());
        assertTrue(userService.getAll().size()==3);
    }

    @Test
    public void testGetUser(){
        when(userRepository.findById(anyLong())).thenReturn(user);

        assertEquals(userService.get(anyLong()), user);
    }

    @Test
    public void testGetUserAndExpectEmptyResultDataAccessException(){
        when(userRepository.findById(anyLong())).thenReturn(null);

        exception.expect(EmptyResultDataAccessException.class);
        userService.get(anyLong());
    }

    @Test
    public void testUpdateUser(){
        when(userRepository.findById(anyLong())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertEquals(user, userService.update(user));
    }

    @Test
    public void testUpdateUserAndExpectEmptyResultDataAccessException(){
        when(userRepository.findById(anyLong())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        exception.expect(EmptyResultDataAccessException.class);
        userService.update(user);
    }

    @Test
    public void testDeleteUser(){
        when(userRepository.findById(anyLong())).thenReturn(user);

        userService.delete(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUserAndExpectEmptyResultDataAccessException(){
        when(userRepository.findById(anyLong())).thenReturn(null);
        doNothing().when(userRepository).delete(any(User.class));

        exception.expect(EmptyResultDataAccessException.class);
        userService.delete(user);
    }

}
