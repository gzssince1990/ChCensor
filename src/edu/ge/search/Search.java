package edu.ge.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.serviceloader.ServiceFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by zhisong on 2/15/2016.
 */
@Service
public class Search{
    public enum Engine{
        QIHU,SOGOU,BAIDU,GOOGLE
    }
    private Engine engine;
    private SSLContext sslContext;
    SSLConnectionSocketFactory sslConnectionSocketFactory;

    private final String USER_AGENT = "Mozilla/5.0";
    private final String BLOCKED_STR_QIHU = "<div class='icon-gantan' id='warning_info'>(.+?)</div>";
    private final String BLOCKED_STR_SOGOU = "<div class=\"topqc\" id=\"orderblack_tip_black_container\"><strong>(.+?)</strong></div>";
    private final String BLOCKED_STR_BAIDU = "<div class=\"boldline se_common_hint c-gap-bottom c-container\" data-id=\"40400\" data-tpl=\"hint_boldline\"><strong>(.+?)</strong></div>";
    private final Pattern BlockedPattern_QIHU = Pattern.compile(BLOCKED_STR_QIHU);
    private final Pattern BlockedPattern_Sogou = Pattern.compile(BLOCKED_STR_SOGOU);
    private final Pattern BlockedPattern_Baidu = Pattern.compile(BLOCKED_STR_BAIDU);
    private final String BLOCKED_KEYWORD = "根据相关法律法规和政策，部分搜索结果未予显示。";

    public Search(Engine engine) {
        this.engine = engine;
        try {
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            sslContext.init(new KeyManager[0], new TrustManager[]{trustManager}, new SecureRandom());
            //SSLContext.setDefault(sslContext);

            sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier()
            );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


    }

    public Search() throws NoSuchAlgorithmException, KeyManagementException {
        sslContext = SSLContext.getInstance("TLS");
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        sslContext.init(new KeyManager[0], new TrustManager[]{trustManager}, new SecureRandom());
        //SSLContext.setDefault(sslContext);

        sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier()
        );
    };

    public static void main(String[] args) throws Exception {

        Search http = new Search(Engine.GOOGLE);

        System.out.println("Testing 1 - Send Http GET request");

        System.out.println(http.isBlocked("tiananmen square massacre"));

        System.out.println(http.isBlocked("falungong"));

        System.out.println(http.isBlocked("xijinping"));

        System.out.println(http.isBlocked("java"));

        System.out.println(http.isBlocked("曹建明"));

        System.out.println(http.isBlocked("西藏"));

        System.out.println(http.isBlocked("西藏独立"));

        System.out.println();

        //System.out.println("\nTesting 2 - Send Http POST request");
        //http.sendPost();

    }

    public Search creatSearch(Engine engine){
        return new Search(engine);
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    // HTTP GET request
    private String sendGet(String queryStr) throws IOException{

        String url;
        String query;

        switch (engine){
            /* Baidu search query url */
            case BAIDU:
                url = "http://www.baidu.com/s?";
                query = URLEncoder.encode("wd") + "=" + URLEncoder.encode(queryStr);
                url += query;
                break;
            /* Sogou Search query url */
            case SOGOU:
                url = "http://www.sogou.com/web?";
                query = URLEncoder.encode("query") + "=" + URLEncoder.encode(queryStr);
                url += query;
                break;
            /* 360 Search query url */
            case QIHU:
                url = "http://www.so.com/s?";
                query = URLEncoder.encode("q") + "=" + URLEncoder.encode(queryStr);
                url += query;
                break;
            /* default is baidu */
            default:
                url = "http://www.baidu.com/s?";
                query = URLEncoder.encode("wd") + "=" + URLEncoder.encode(queryStr);
                url += query;
                break;
        }


        //Create http client
        //CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpClient client = HttpClientBuilder.create()
                .setSSLSocketFactory(sslConnectionSocketFactory).build();

        //Send get request
        HttpGet request = new HttpGet(url);

        //Add request header
        request.addHeader("User-Agent", USER_AGENT);

        //Get the response from server
        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        //Transfer response to String
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line+'\n');
        }

        return result.toString();

    }

    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "https://selfsolve.apple.com/wcResults.do";

        CloseableHttpClient client = HttpClientBuilder.create().build();
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

    @Async
    public Boolean isBlockedAsync(String queryStr) throws InterruptedException {

        System.out.println("Entering idBlocked method");
        Thread.sleep(5000);

        try {
            Matcher matcher;
            switch (engine){
                case QIHU:
                    matcher = BlockedPattern_QIHU.matcher(sendGet(queryStr));
                    break;
                case SOGOU:
                    matcher = BlockedPattern_Sogou.matcher(sendGet(queryStr));
                    break;
                case BAIDU:
                    matcher = BlockedPattern_Baidu.matcher(sendGet(queryStr));
                    break;
                default:
                    matcher = BlockedPattern_Baidu.matcher(sendGet(queryStr));
                    break;
            }
            String result = matcher.find()? matcher.group(1): "";
            System.out.println("Leaving isBlocked method successfully");
            //return new AsyncResult<Boolean>(result.equals(BLOCKED_KEYWORD));
            return result.equals(BLOCKED_KEYWORD);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println();
            System.out.println("Leaving isBlocked method inproperly");
            //return new AsyncResult<Boolean>(false);
            return false;
        }
    }

    @Async
    public void testAsync() throws InterruptedException {
        System.out.println("Enter testAsync");
        Thread.sleep(20000);
        System.out.println("Leaving testAsync");
    }

    public boolean isBlocked(String queryStr) {

        System.out.println("Entering idBlocked method");

        try {
            Matcher matcher;
            switch (engine){
                case QIHU:
                    matcher = BlockedPattern_QIHU.matcher(sendGet(queryStr));
                    break;
                case SOGOU:
                    matcher = BlockedPattern_Sogou.matcher(sendGet(queryStr));
                    break;
                case BAIDU:
                    matcher = BlockedPattern_Baidu.matcher(sendGet(queryStr));
                    break;
                default:
                    matcher = BlockedPattern_Baidu.matcher(sendGet(queryStr));
                    break;
            }
            String result = matcher.find()? matcher.group(1): "";
            System.out.println("Leaving isBlocked method successfully");
            return result.equals(BLOCKED_KEYWORD);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println();
            System.out.println("Leaving isBlocked method inproperly");
            return false;
        }
    }

}
