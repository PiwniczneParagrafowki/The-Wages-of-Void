package pl.piwniczneparagrafowki.thewagesofvoid.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;

import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User save(User user);

    User findById(long id);

    List<User> findAll();

    void delete(User user);
    
}
