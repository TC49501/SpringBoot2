package com.dpd.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by txc68 on 4/17/2017.
 */
public class HttpUrlPingPongTest {
    private static Pattern patternTag, patternLink;
    private static Matcher matcherTag, matcherLink;
    private static int totalPongs = 0;
    private static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
    private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

    public static void main(String[] args) throws Exception {
        patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
        patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);

        String url = "https://api.walletinsights.com/";
        HttpUrlPingPongTest http = new HttpUrlPingPongTest();
        String pageResponse = http.getURLResponse(url);

        ArrayList<String> linkTexts = grabHTMLLinkText(pageResponse);
        for (String hl : linkTexts) {
            String url2 = url + "/" + hl + "/ping";
            String httpResp = http.getURLResponse(url2);
            if (httpResp.indexOf("pong") >= 0) {
                System.out.println("has pong : " + url2);
                totalPongs++;
            }
        }
        System.out.println("Total pongs : " + totalPongs);
    }

    private String getURLResponse(String url) {
        StringBuffer response = new StringBuffer();
        try {
            System.setProperty("http.proxyHost", "172.22.240.68");
            System.setProperty("http.proxyPort", "18717");
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error URLs:" + url);
        }
        return response.toString();
    }

    private static ArrayList<String> grabHTMLLinkText(final String html) {
        ArrayList<String> result = new ArrayList<>();
        matcherTag = patternTag.matcher(html);
        while (matcherTag.find()) {
            String href = matcherTag.group(1); // href
            String linkText = matcherTag.group(2); // link text
            matcherLink = patternLink.matcher(href);
            result.add(linkText);
        }
        return result;

    }
}
