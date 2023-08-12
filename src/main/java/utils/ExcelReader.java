package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ExcelReader {

    public static String getExcelData(String label,String header){
        String value = null;

        try{
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/test/Resources/Datasheet1.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("DevopsUniv");
            int noofRows = sheet.getPhysicalNumberOfRows();
            int noofCols = sheet.getRow(0).getLastCellNum();

            String[] LabelValue = new String[noofRows];
            String[] HeaderValue = new String[noofCols];

//            for (int i = 0; i < noofRows; i++) {
//                LabelValue[i] = sheet.getRow(i).getCell(0).getStringCellValue();
//             //   System.out.println(sheet.getRow(i).getCell(0).getStringCellValue());
//            }
//            System.out.println(Arrays.toString(LabelValue));
//            for (int j = 0; j < noofCols; j++) {
//                HeaderValue[j] = sheet.getRow(0).getCell(j).getStringCellValue();
//                //System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
//            }

            for (int i = 0; i < noofRows; i++) {
                LabelValue[i] = sheet.getRow(i).getCell(0).getStringCellValue();
                if(LabelValue[i].equalsIgnoreCase(label)){
                    for (int j = 0; j < noofCols; j++) {
                        HeaderValue[j] = sheet.getRow(0).getCell(j).getStringCellValue();
                        if(HeaderValue[j].equalsIgnoreCase(header)){
                            value = sheet.getRow(i).getCell(j).getStringCellValue();
                            break;
                        }
                    }
                break;
                }

                
                            }

                //System.out.println(Arrays.toString(HeaderValue));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return value;
    }

    public static void main(String[] args) {
        System.out.println(getExcelData("Microsoft","URL"));
    }

}
