package edu.ge.search;

import edu.ge.IO.IO;

/**
 * Created by zhisong on 2/29/16.
 */
public class Driver {
    public static void main(String[] args) {
        String orgText = IO.readFile(".","google-10000-english.txt");

        String[] orgWords = orgText.split("\n");

        Search http = new Search();

        for (int i = 0; i < orgWords.length; i++) {
            System.out.println(orgWords[i] + " " + http.isBlocked(orgWords[i]));
        }
    }
}
