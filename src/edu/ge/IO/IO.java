package edu.ge.IO;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import java.io.*;

/**
 * Created by Zhisong on 2/4/2016.
 */
public class IO {
    public static String readFile(String dirStr, String filename){
        String result= new String();
        File dir = new File(dirStr);
        File file = new File(dir, filename);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            while ((line= in.readLine()) != null){
                result += line + '\n';
                //System.out.println(result);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public static boolean writeFile(String dirStr, String filename, String text){
        File dir = new File(dirStr);
        File file = new File(dir, filename);
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"),true);
            out.println(text);
            //out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        //System.out.println(readFile(".", "list.txt"));

        //readExcelFile();

        LineBag bag = new LineBag();
        Reader reader = new Reader(bag, "reader", "list.txt");
        Writer writer = new Writer(bag, "writer", "");

        try {
            System.out.println("Starting join");
            reader.getThread().join();
            System.out.println("reader joined");
            writer.getThread().join();
            System.out.println("writer joined");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program ended");

    }

}