package com.gamma.dexter.musicRecommendation;

import com.gamma.dexter.console.draft.JSONWrapper;
import net.sf.json.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Debashish Sen on 17-Mar-17.
 */

public class HttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String post(String url, String data) throws IOException {

        HttpClient client = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);

        httpost.setHeader("type", "POST");
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");
        httpost.setHeader("dataType", "json");
        httpost.setEntity(new StringEntity(data));

        HttpResponse response = client.execute(httpost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity);
        }

        return "";
    }

    public static String get(String url) throws IOException {

        HttpClient client = HttpClients.createDefault();
        HttpGet httGet = new HttpGet(url);

        httGet.setHeader("type", "GET");

        HttpResponse response = client.execute(httGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity);
        }

        return "";
    }

    public static String sendHttpPost(String url, String msg) throws IOException,
            NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return sendHttpPost(url, msg, new HashMap<String, String>());
    }

    public static String sendHttpsGetWithProxy(String hostStr, String urlPart, String msg, Map<String, String> header, Map<String, String> proxyDet) throws IOException {

        HttpHost proxy = new HttpHost(proxyDet.get("ip"), Integer.parseInt(proxyDet.get("port")), "http");

        CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxy).build();

        try {
            HttpHost host = new HttpHost(hostStr, 443, "https");
            HttpGet get = new HttpGet(urlPart);

            for (String key : header.keySet()) {
                get.setHeader(key, header.get(key));
            }

            System.out.println("executing request to " + hostStr + urlPart + " via " + proxy);
            HttpResponse response = httpClient.execute(host, get);
            logger.info("Response Status line : " + response.getStatusLine());

            HttpEntity entity = response.getEntity();

            String res = null;
            if (entity != null) {
                res = EntityUtils.toString(entity);
            }

            return res;

        } finally {
            httpClient.close();
        }
    }

    public static String sendHttpsPostWithProxy(String hostStr, String urlPart, String msg, Map<String, String> header, Map<String, String> proxyDet) throws IOException {

        HttpHost proxy = new HttpHost(proxyDet.get("ip"), Integer.parseInt(proxyDet.get("port")), "http");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxy).build()) {
            HttpHost host = new HttpHost(hostStr, 443, "https");
            HttpPost post = new HttpPost(urlPart);

            post.setHeader("type", "POST");
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            post.setHeader("dataType", "json");

            for (String key : header.keySet()) {
                post.setHeader(key, header.get(key));
            }

            post.setEntity(new StringEntity(msg, Charset.forName("UTF-8")));

            System.out.println("executing request to " + hostStr + urlPart + " via " + proxy);
            HttpResponse response = httpClient.execute(host, post);
            logger.info("Response Status : " + response.getStatusLine());

            HttpEntity entity = response.getEntity();

            String res = null;
            if (entity != null) {
                res = EntityUtils.toString(entity);
            }

            return res;
        }
    }

    public static String sendHttpPost(String url, String msg, Map<String, String> header) throws IOException {

        logger.info("Post url -> " + url);

        HttpClient client = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);

        httpost.setHeader("type", "POST");
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");
        httpost.setHeader("dataType", "json");

        for (String key : header.keySet()) {
            httpost.setHeader(key, header.get(key));
        }

        httpost.setEntity(new StringEntity(msg, Charset.forName("UTF-8")));

        HttpResponse response = client.execute(httpost);

        HttpEntity entity = response.getEntity();

        String res = null;
        if (entity != null) {
            res = EntityUtils.toString(entity, Charsets.UTF_8);
        }
        return res;
    }

    public static String sendHttpGet(String url) throws IOException {
        logger.info("Sending msg to url -> " + url);

        org.apache.http.client.HttpClient client = HttpClients.createDefault();
        org.apache.http.client.methods.HttpGet httpGet =
                new org.apache.http.client.methods.HttpGet(url);

        org.apache.http.HttpResponse response = client.execute(httpGet);

        HttpEntity entity = response.getEntity();

        String res = null;
        if (entity != null) {
            res = EntityUtils.toString(entity);
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
//        Map<Integer,Integer> map =new HashMap<>();
//        map.put(1,2);
//        map.put(3,4);
//       HttpUtil httpUtil =new HttpUtil();
//        httpUtil.sendRatings(map);
//        httpUtil.getRecommendation();
    }
    public JSONObject getRecommendation() throws Exception{
//        String recommendation= HttpUtil.get("http://192.168.1.4:5432/0/ratings/top/5");
//        recommendation= recommendation.replaceAll("\\\\","").replaceAll("\"\"","\"");
//        System.out.println(recommendation);
//        JSONObject jsonObject = (JSONObject) JSONWrapper.getJSON("Payload", recommendation);
//        return jsonObject;
        return null;
    }
    public void sendRatings(Map<Integer,Integer> mapOfRatings) throws Exception{
        String str="";
        for(Map.Entry<Integer,Integer> entry: mapOfRatings.entrySet()){
            str+=entry.getKey()+","+entry.getValue()+"\n";
        }
        System.out.println(str);
//        String t= HttpURLConnectionExample.post("localhost:5432/0/ratings", str);
//        System.out.println(t);
    }
}