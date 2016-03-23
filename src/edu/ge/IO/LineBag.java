package edu.ge.IO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by zhisong on 3/22/2016.
 */
public class LineBag {
    private Queue<String> bag;
    private boolean isEnd;

    private final int MAX_SIZE = 10;

    public LineBag(){
        bag = new LinkedList<String>();
        isEnd = false;
    }

    public synchronized void add(String line){
        if (bag.size() >= MAX_SIZE){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        bag.add(line);
        System.out.println("Added: " + line);
        notify();
    }

    public synchronized String remove(){
        if (bag.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Removing: " + bag.peek());
        notify();
        return bag.poll();
    }

    public synchronized void stop(){
        while (!bag.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isEnd = true;
        notify();
    }

    public boolean isEnd() {
        return isEnd;
    }
}
