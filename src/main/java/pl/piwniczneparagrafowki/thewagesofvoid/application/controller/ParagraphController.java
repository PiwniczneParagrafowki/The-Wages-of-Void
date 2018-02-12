package pl.piwniczneparagrafowki.thewagesofvoid.application.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.ParagraphService;

import javax.annotation.Resource;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@RestController
@RequestMapping("/api")
public class ParagraphController {

    private static final Log LOG = LogFactory.getLog(ParagraphController.class);

    @Resource
    ParagraphService paragraphService;

    @RequestMapping(value = "/paragraph/{id}")
    public String getParagraph(@PathVariable("id") long id) {
        LOG.info("GET /paragraph/{id} id:" + id);
        String paragraph = paragraphService.getParagraph(id).getContent();
        return paragraph;
    }

}
