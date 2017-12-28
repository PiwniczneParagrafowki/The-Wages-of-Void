package pl.piwniczneparagrafowki.thewagesofvoid.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@RestController
public class StartController {

    @RequestMapping("/")
    public String index() {
        return "Witamy w The Wages of Void - przeglądarkowej grze paragrafowej!";
    }

}
