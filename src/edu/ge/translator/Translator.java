package edu.ge.translator;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Zhisong on 2/3/2016.
 */
@Service
public class Translator {

    private static Locale srcLanguage = Locale.CHINESE;
    private static Locale dstLanguage = Locale.ENGLISH;

    public String translate(String text){
        String ret = null;

        try {
            String query = URLEncoder.encode(text, "UTF-8");
            String langPair = URLEncoder.encode(srcLanguage.getLanguage()
            + "|" + dstLanguage.getLanguage(), "UTF-8");
            String url = "http://api.mymemory.translated.net/get?q="+query+"&langpair="
                    +langPair;
            HttpClient hc = new DefaultHttpClient();
            HttpGet hg = new HttpGet(url);
            HttpResponse hr = hc.execute(hg);
            if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                JSONObject response = new JSONObject(EntityUtils.toString(hr.getEntity()));
                ret = response.getJSONObject("responseData").getString("translatedText");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String srcStr = in.next();
        Translator translator = new Translator();
        String result = translator.translate(srcStr);

        System.out.println(result);
    }
}
