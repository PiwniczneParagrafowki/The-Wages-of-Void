package pl.piwniczneparagrafowki.thewagesofvoid.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.service.ParagraphService;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Service
public class ParagraphsDataMigration {

    private static final Log LOG = LogFactory.getLog(ParagraphsDataMigration.class);

    @Resource
    ParagraphService paragraphService;

    public ParagraphsDataMigration() throws IOException {
    }

    public void migrate(){
        System.out.println("Migration started");

        File file = new File(getClass().getClassLoader().getResource("paragraphs/paragraphs.xls").getFile());

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell parNr, parContent;

            int rows; 
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0;
            int tmp = 0;

            for(int i = 1; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if(row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(tmp > cols) cols = tmp;
                }
            }

            for(int r = 1; r < rows; r++) {
                row = sheet.getRow(r);
                if(row != null) {
                        parNr = row.getCell(0);
                        parContent = row.getCell(1);
                        if(parNr != null & parContent != null) {
                            paragraphService.save((long)parNr.getNumericCellValue(), parContent.getStringCellValue());
                        }
                }
            }
        } catch(IOException ioe) {
            LOG.error(ioe);
        }
    }

}
