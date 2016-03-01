package edu.ge.translator;

import edu.ge.IO.IO;

/**
 * Created by Zhisong on 2/4/2016.
 */
public class Driver {
    public static void main(String[] args) {
        String orgText = IO.readFile(".","test.txt");

        String[] orgWords = orgText.split("\n");


        for (int i = 0; i < orgWords.length; i++) {
            //System.out.println(orgWords[i] + '\t' + Translator.translate(orgWords[i]));
            IO.writeFile(".", "out.txt", orgWords[i] + '\t' + Translator.translate(orgWords[i]));
        }

    }
}
