package edu.ge.IO;


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
        System.out.println(readFile(null, "test.txt"));
    }
}
