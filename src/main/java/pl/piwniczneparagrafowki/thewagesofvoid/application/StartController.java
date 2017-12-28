package pl.piwniczneparagrafowki.thewagesofvoid.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@RestController
public class StartController {

    private static final Log LOG = LogFactory.getLog(StartController.class);

    @RequestMapping("/")
    public String index() {
        LOG.info("GET /");
        return "Witamy w The Wages of Void - przeglądarkowej grze paragrafowej!";
    }

}
