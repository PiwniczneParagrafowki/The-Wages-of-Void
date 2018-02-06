package pl.piwniczneparagrafowki.thewagesofvoid.application.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Repository
public class CharacterDaoImpl implements CharacterDao {

    private static final Logger logger = LoggerFactory.getLogger(CharacterDaoImpl.class);

//    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Character.class).buildSessionFactory();

    @Override
    public void add(Character character) {

    }

    @Override
    public void delete(Character character) {

    }

    @Override
    public void update(Character character) {

    }

    @Override
    public Character get(int id) {
        return null;
    }

    @Override
    public List<Character> getAll() {
        return null;
    }


}
