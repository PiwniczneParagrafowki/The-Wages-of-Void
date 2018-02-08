package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.UserRepository;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    private static MessageDigest md;

    @Resource
    UserRepository userRepository;

    @Override
    public User create(User user){
        String cryptedPassword = cryptWithMD5(user.getPassword());
        if (cryptedPassword==null) {
            throw new ValidationException("CREATE FAILED: Failed validation for the user password.");
        } else {
            user.setPassword(cryptedPassword);
        }
        if(userRepository.findById(user.getId())==null) {
            userRepository.save(user);
        } else {
            throw new DuplicateKeyException("CREATE FAILED: Object with id=" + user.getId() + " already exist in the database.");
        }
        return user;
    }

    @Override
    public User update(User user) {
        User oldUser;
        oldUser = userRepository.findById(user.getId());
        if(oldUser!=null) {
            userRepository.save(user);
        } else {
            throw new EmptyResultDataAccessException("UPDATE FAILED: User with id=" + user.getId() + " not found in the database.", 1);
        }
        return user;
    }

    @Override
    public User get(long id) {
        User user;
        user = userRepository.findById(id);
        if(user==null) {
            throw new EmptyResultDataAccessException("GET FAILED: User with id=" + id + " not found in the database.", 1);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        return users;
    }

    @Override
    public void delete(User user) {
        User tmpUser;
        tmpUser = userRepository.findById(user.getId());
        if(tmpUser!=null){
            userRepository.delete(user);
        } else {
            throw new EmptyResultDataAccessException("GET FAILED: User with id=" + user.getId() + " not found in the database.", 1);
        }
    }

    public static String cryptWithMD5(String pass){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            LOG.error(ex);
        }
        return null;
    }
    
}
