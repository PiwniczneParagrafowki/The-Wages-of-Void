package pl.piwniczneparagrafowki.thewagesofvoid.application.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Paragraph;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.ParagraphService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/paragraph/", method = RequestMethod.GET)
    public ResponseEntity<List<Paragraph>> getAllParagraphs() {
        LOG.info("GET /paragraph/");
        List<Paragraph> paragraphs = new ArrayList<>();
        paragraphs = paragraphService.getAllParagraphs();
        if(paragraphs.isEmpty()) return new ResponseEntity<List<Paragraph>>(paragraphs, HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Paragraph>>(paragraphs, HttpStatus.OK);
    }

}
