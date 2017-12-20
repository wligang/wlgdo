package com.wlgdo.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/* 
 * 利用HttpClient进行post请求的工具类 
 */
public class HttpClientUtil {
    public static String doPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            // 设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN",
                "wpOm3OJUb3mNQmCSEkTUjjff_PWj0LBCU6OTDd2HVwyqrZY863j7ocg7YjLRhrqQii2A39GufRmNnI6Ib1J0gWB927nlyH-DaEjx_dLLGokBABdAEAPOP");
        JSONObject json = new JSONObject();
        json.put("touser", "ovDvrvnIwxNJYH-IPoimRmJXRSuM");
        json.put("template_id", "Ae4LJU798DyFNSm5-sAJWOS0IN_b632rTXEEEBVNtww");
        json.put("topcolor", "#FF0000");
        json.put("url", "https://www.wlgdo.com/awards/award.html#0");

        JSONObject data = new JSONObject();

        JSONObject orgName = new JSONObject();
        orgName.put("value", "王利刚");
        orgName.put("color", "#173177");
        data.put("orgName", orgName);

        JSONObject awardName = new JSONObject();
        orgName.put("value", "双12精彩大放送");
        orgName.put("color", "#173177");

        data.put("orgName", orgName);
        data.put("awardName", awardName);
        json.put("data", data);

        String httpOrgCreateTestRtn = doPost(url, MapUtils.objectToMap(new HashMap<String, String>(), json), "utf-8");
        System.out.println("result:" + httpOrgCreateTestRtn);
    }
}