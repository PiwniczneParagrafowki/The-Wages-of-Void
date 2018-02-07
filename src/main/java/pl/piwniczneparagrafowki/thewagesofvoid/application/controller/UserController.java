package pl.piwniczneparagrafowki.thewagesofvoid.application.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.User;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Log LOG = LogFactory.getLog(UserController.class);

    @Resource
    UserService userService;

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        LOG.info("GET /api/user/");
        List<User> users;
        users = userService.getAll();
        if(users.isEmpty()) return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        LOG.info("GET /api/user/{id} id:"+id);
        User user = userService.get(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOG.info("POST /user/ user:" + user.getName());
        userService.create(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        LOG.info("PUT /user/{id} id:"+id);
        if(user.getId()!=id) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userService.update(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        LOG.info("DELETE /user/ user:" + user.getName());
        userService.delete(user);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
