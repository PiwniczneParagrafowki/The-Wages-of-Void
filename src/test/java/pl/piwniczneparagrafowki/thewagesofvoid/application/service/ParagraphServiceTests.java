package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Paragraph;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.ParagraphRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class ParagraphServiceTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    ParagraphRepository paragraphRepository;

    @Mock
    Paragraph paragraph;

    @Mock
    List<Paragraph> paragraphs;

    @InjectMocks
    ParagraphServiceImpl paragraphService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetParagraph() {
        when(paragraphRepository.findById(anyLong())).thenReturn(paragraph);

        assertEquals(paragraph, paragraphService.getParagraph(anyLong()));
    }

    @Test
    public void testGetParagraphAndThrowEmptyResultDataAccessException() {
        when(paragraphRepository.findById(anyLong())).thenReturn(null);

        exception.expect(EmptyResultDataAccessException.class);
        paragraphService.getParagraph(anyLong());
    }

    @Test
    public void testGetAllParagraphs() {
        when(paragraphRepository.findAll()).thenReturn(paragraphs);

        assertEquals(paragraphs, paragraphService.getAllParagraphs());
    }

}
