package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;

import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public interface UserService {

    User create(User user);

    User update(User user);

    User get(long id);

    List<User> getAll();

    void delete(User user);
    
}
