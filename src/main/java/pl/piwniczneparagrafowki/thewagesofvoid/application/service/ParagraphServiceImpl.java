package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Paragraph;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.ParagraphRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Service
public class ParagraphServiceImpl implements ParagraphService {

    @Resource
    ParagraphRepository paragraphRepository;

    @Override
    public Paragraph getParagraph(long id) {
        Paragraph paragraph;
        paragraph = paragraphRepository.findById(id);
        if(paragraph == null) {
            throw new EmptyResultDataAccessException("GET FAILED: Paragraph with id=" + id + " not found in the database.", 1);
        }
        return paragraph;
    }

    @Override
    public List<Paragraph> getAllParagraphs() {
        return paragraphRepository.findAll();
    }
}
