package com.example.utils;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by kaixu on 2017/8/11.
 */
public class JsoupHelper {
    public static Connection getConnection(String url, Map<String, String> params, String charset,
                                           Map<String, String> headers) {
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iter = params.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                paramList.add(new BasicNameValuePair(key, value));
            }
            try {
                String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(paramList, charset));
                StringBuffer sb = new StringBuffer();
                sb.append(url);
                if (url.indexOf("?") > 0) {
                    sb.append("&");
                } else {
                    sb.append("?");
                }
                sb.append(paramStr);
                url = sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Connection conn = Jsoup.connect(url);
        conn.timeout(10000); // 10秒超时
        conn.ignoreContentType(true);

        if (headers != null) {
            Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                conn.header(key, value);
            }
        }

        return conn;
    }

    public static String get(String url, Map<String, String> params, String charset,
                             Map<String, String> headers) {
        String result = "";
        try {
            Connection conn = getConnection(url, params, charset, headers);
            Response response = conn.execute();
            result = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String[] getJsonToStringArray(JSONArray jsonArray) {
        String[] arr = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            arr[i] = jsonArray.getString(i);
        }
        return arr;
    }


}
