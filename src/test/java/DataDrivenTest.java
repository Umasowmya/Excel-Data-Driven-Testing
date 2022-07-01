import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDrivenTest {

    public ArrayList<String> getData(String testCase) throws IOException {

        ArrayList<String> a = new ArrayList<>();
        FileInputStream file = new FileInputStream("./resource/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        int sheets = workbook.getNumberOfSheets();
        System.out.println("Total number of sheets " + sheets);
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equals("TestData")) {

                XSSFSheet sheet = workbook.getSheetAt(i);
                System.out.println("TestData is  found at  " + i);
                Iterator<Row> rows = sheet.iterator();

                Iterator<Cell> cell = rows.next().cellIterator();

                int k = 0, column = -1;

                while (cell.hasNext()) {
                    Cell value = cell.next();
                    if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
                        column = k;
                    }
                    k++;
                }
                System.out.println("Test cases column is " + column);

                int purchaseRow = -1, rowCount = 0;

                while (rows.hasNext()) {
                    Row r = rows.next();
                    rowCount++;
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCase)) {
                        Iterator<Cell> values = r.cellIterator();
                        purchaseRow = rowCount;

                        while (values.hasNext()) {
                            Cell c = values.next();
                            if (c.getCellType() == CellType.STRING) {
                                a.add(c.getStringCellValue());
                            } else {

                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

                            }
                        }
                    }
                }
                    System.out.println(testCase+" row is " + purchaseRow);

                }
            }



            return a;
        }


}
