package edu.ge.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 * Created by zhisong on 2/15/2016.
 */


public class Search {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        Search http = new Search();

        System.out.println("Testing 1 - Send Http GET request");

        System.out.println(http.isBlocked("tiananmen square massacre"));

        System.out.println(http.isBlocked("falungong"));

        System.out.println(http.isBlocked("xijinping"));

        System.out.println(http.isBlocked("java"));



        System.out.println();

        //System.out.println("\nTesting 2 - Send Http POST request");
        //http.sendPost();

    }

    // HTTP GET request
    private String sendGet(String queryStr) throws Exception {

        //String url = "http://www.google.com/search?q=developer";

        String url = "http://www.baidu.com/s?";

        String query = URLEncoder.encode("wd") + "=" + URLEncoder.encode(queryStr);

        url += query;



        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line+'\n');
        }

        //System.out.println(result.toString());
        return result.toString();

    }

    /*
    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "https://selfsolve.apple.com/wcResults.do";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
        urlParameters.add(new BasicNameValuePair("cn", ""));
        urlParameters.add(new BasicNameValuePair("locale", ""));
        urlParameters.add(new BasicNameValuePair("caller", ""));
        urlParameters.add(new BasicNameValuePair("num", "12345"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line+'\n');
        }

        System.out.println(result.toString());

    }
    */

    final String BLOCKED_STR = "<div class=\"boldline se_common_hint c-gap-bottom c-container\" data-id=\"40400\" data-tpl=\"hint_boldline\"><strong>(.+?)</strong></div>";
    final Pattern BlockedPattern = Pattern.compile(BLOCKED_STR);
    final String BLOCKED_KEYWORD = "根据相关法律法规和政策，部分搜索结果未予显示。";

    public boolean isBlocked(String queryStr){
        try {
            Matcher matcher = BlockedPattern.matcher(sendGet(queryStr));
            String result = matcher.find()? matcher.group(1): "";
            return result.equals(BLOCKED_KEYWORD);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
