package pl.piwniczneparagrafowki.thewagesofvoid.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Paragraph;

import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Repository
public interface ParagraphRepository extends CrudRepository<Paragraph, Long> {

    Paragraph findById(long id);

    List<Paragraph> findAll();

    Paragraph save(Paragraph paragraph);

}
