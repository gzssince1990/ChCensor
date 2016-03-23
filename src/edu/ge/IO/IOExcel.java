package edu.ge.IO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhisong on 3/20/2016.
 */
public class IOExcel {
    private String fileName;
    POIFSFileSystem fs;
    HSSFWorkbook wb;
    HSSFSheet sheet;


    public IOExcel(String fileName) throws IOException {
        this.fileName = fileName;

        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }

        fs = new POIFSFileSystem(new FileInputStream(fileName));
        wb = new HSSFWorkbook(fs);
        sheet = wb.getSheetAt(0);
    }


    public void writeRow(int rowNum, Object[] objects){

        HSSFRow row = sheet.createRow(rowNum);

        int cellNum = 0;
        for (Object obj: objects){
            HSSFCell cell = row.createCell(cellNum++);
            if (obj instanceof String){
                cell.setCellValue((String) obj);
            }else if (obj instanceof Integer){
                cell.setCellValue((Integer) obj);
            }else if (obj instanceof Double){
                cell.setCellValue((Double) obj);
            }else if (obj instanceof Float){
                cell.setCellValue((Float) obj);
            }else if (obj instanceof Boolean){
                cell.setCellValue((Boolean) obj);
            }else if (obj instanceof Date){
                cell.setCellValue((Date) obj);
            }
        }

        try {
            wb.write(new FileOutputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void readExcelFile(){
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("test.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows;
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0;
            int tmp = 0;

            for (int i = 0; i < 10 || i <rows; i++) {
                row = sheet.getRow(i);
                if (row != null){
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }

            for (int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null){
                    for (int c = 0; c < cols; c++) {
                        cell = row.getCell(c);
                        if (cell != null){
                            switch (cell.getCellType()){
                                case Cell.CELL_TYPE_BLANK:
                                    System.out.print("\t");
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    System.out.print(cell.getBooleanCellValue()+"\t");
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    System.out.print(cell.getNumericCellValue()+"\t");
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    System.out.print(cell.getStringCellValue()+"\t");

                                    break;
                            }
                        }else {
                            System.out.print("\t");
                        }
                    }
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args){

        try {
            IOExcel ioExcel = new IOExcel("test.xls");
            ioExcel.writeRow(17, new Object[]{"a","test","b", 1, 5.5});
            ioExcel.writeRow(20, new Object[]{"asffsd","test","b", true});
            ioExcel.writeRow(21, new Object[]{"ad","test","b", Calendar.getInstance().getTime()});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
