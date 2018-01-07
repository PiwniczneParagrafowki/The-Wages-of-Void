package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.dao.CharacterDao;
import pl.piwniczneparagrafowki.thewagesofvoid.application.dao.CharacterDaoImpl;

import javax.annotation.Resource;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    @Resource
    CharacterDaoImpl characterDao;

}
