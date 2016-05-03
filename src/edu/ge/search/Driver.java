package edu.ge.search;

import edu.ge.IO.IO;
import edu.ge.IO.IOExcel;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by zhisong on 2/29/16.
 */
public class Driver {
    public static void main(String[] args) throws IOException{
        /* Check words in google-10000-english.txt
        String orgText = IO.readFile(".","google-10000-english.txt");

        String[] orgWords = orgText.split("\n");

        Search http = new Search(Search.Engine.BAIDU);

        for (int i = 0; i < orgWords.length; i++) {
            System.out.println(orgWords[i] + " " + http.isBlocked(orgWords[i]));
        }
        */

        Calendar calendar = Calendar.getInstance();

        IOExcel ioExcel = new IOExcel("test1.xls");

        String orgText = IO.readFile(".","list.txt");

        String[] orgWords = orgText.split("\n");

        Search http = new Search(Search.Engine.BAIDU);

        ioExcel.writeRow(0, new Object[]{"Word", "Is blocked", "Date"});

        for (int i = 0; i < orgWords.length; i++) {
            boolean isBlocked = http.isBlocked(orgWords[i]);
            System.out.println(orgWords[i] + " " + isBlocked);
            ioExcel.writeRow(i+1, new Object[]{orgWords[i], isBlocked, calendar.getTime()});
        }




    }
}
