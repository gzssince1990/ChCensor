package edu.ge.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by zhisong on 3/22/2016.
 */
public class Reader implements Runnable {
    private LineBag bag;
    private Thread thread;
    private String fName;

    public Reader(LineBag bag, String tName, String fName){
        this.bag = bag;
        this.fName = fName;
        thread = new Thread(this, tName);
        thread.start();
    }

    public Thread getThread() {
        return thread;
    }

    public void run() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(fName));
            for (String line; (line = bf.readLine()) != null;){
                bag.add(line);
            }

            bag.stop();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
