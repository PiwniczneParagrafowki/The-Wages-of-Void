package pl.piwniczneparagrafowki.thewagesofvoid.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.CharacterService;

import javax.annotation.Resource;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@RestController
@RequestMapping("/api")
public class CharacterController {

    @Resource
    CharacterService characterService;

}
