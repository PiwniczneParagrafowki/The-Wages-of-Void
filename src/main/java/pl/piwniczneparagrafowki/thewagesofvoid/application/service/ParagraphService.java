package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Paragraph;

import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public interface ParagraphService {

    Paragraph getParagraph(long id);

    List<Paragraph> getAllParagraphs();

    void save(long id, String content);

}
