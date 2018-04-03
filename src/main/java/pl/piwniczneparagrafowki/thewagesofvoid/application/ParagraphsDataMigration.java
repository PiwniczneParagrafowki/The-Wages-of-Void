package pl.piwniczneparagrafowki.thewagesofvoid.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class ParagraphsDataMigration {

    private static final Log LOG = LogFactory.getLog(ParagraphsDataMigration.class);

    public ParagraphsDataMigration() throws IOException {
    }

    public void migrate(){
        System.out.println("Migration started");

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("/home/arek/The-Wages-of-Void/src/main/resources/paragraphs/paragraphs.ods"));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for(int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if(row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(tmp > cols) cols = tmp;
                }
            }

            for(int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                if(row != null) {
                    for(int c = 0; c < cols; c++) {
                        cell = row.getCell((short)c);
                        if(cell != null) {
                            System.out.println("Forumla: " + cell.getCellFormula());
                        }
                    }
                }
            }
        } catch(IOException ioe) {
            LOG.error(ioe);
        }
    }

}
