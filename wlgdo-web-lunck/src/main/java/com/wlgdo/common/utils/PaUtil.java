package com.wlgdo.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信公众账号工具类
 */
public class PaUtil {

    static Logger log = LoggerFactory.getLogger(PaUtil.class);

    /**
     * 验证微信发送消息的真实性
     * 
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public static String access(String token, String signature, String timestamp, String nonce, String echostr) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp)
                || StringUtils.isEmpty(nonce) || StringUtils.isEmpty(echostr)) {
            return "消息验证失败，请检查";
        }

        String[] str = { token, timestamp, nonce };
        // 字典序排序
        Arrays.sort(str);
        String encryptText = str[0] + str[1] + str[2];
        // SHA1加密
        String decryptText = EncryptionUtil.sha1Encrypt(encryptText);
        // 验证请求来至微信
        if (decryptText.equals(signature)) {
            return echostr;
        }
        return "微信推送消息时,验证消息真实性失败,非法请求\r\n";
    }

    public static boolean verify(String token, String signature, String timestamp, String nonce, String echostr) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp)
                || StringUtils.isEmpty(nonce) || StringUtils.isEmpty(echostr)) {
            return false;
        }
        String[] str = { token, timestamp, nonce };
        // 字典序排序
        Arrays.sort(str);
        String encryptText = str[0] + str[1] + str[2];
        // SHA1加密
        String decryptText = EncryptionUtil.sha1Encrypt(encryptText);
        // 确认请求来至微信
        if (decryptText.equals(signature)) {
            return true;
        }
        return false;
    }

    public static String transferCustomerService(String toUserName, String fromUserName, Long createTime) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
        sb.append("<CreateTime>" + createTime + "</CreateTime>");
        sb.append("<MsgType><![CDATA[transfer_customer_service]]></MsgType>");
        sb.append("</xml>");
        return sb.toString();
    }

    public static String responseText(String toUserName, String fromUserName, Long createTime, String content) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
        sb.append("<CreateTime>" + createTime + "</CreateTime>");
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append("<Content><![CDATA[" + content + "]]></Content>");
        sb.append("</xml>");
        return sb.toString();
    }

    public static String responseNews(String toUserName, String fromUserName, Long createTime, String title, String description,
            String picUrl, String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>");
        sb.append("<CreateTime>" + createTime + "</CreateTime>");
        sb.append("<MsgType><![CDATA[news]]></MsgType>");
        sb.append("<ArticleCount>1</ArticleCount>");
        sb.append("<Articles>");
        sb.append("<item>");
        sb.append("<Title><![CDATA[" + title + "]]></Title>");
        sb.append("<Description><![CDATA[" + description + "]]></Description>");
        sb.append("<PicUrl>" + picUrl + "</PicUrl>");
        sb.append("<Url><![CDATA[" + url + "]]></Url>");
        sb.append("</item>");
        sb.append("</Articles>");
        sb.append("</xml>");
        return sb.toString();
    }

    public synchronized static String getMD5Url() {
        Random random = new Random();
        String s = ("" + System.currentTimeMillis() + random.nextInt(99999999));
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String result = new String(str).substring(8, 24);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFourRandom() {
        Random random = new Random();
        return ("" + random.nextInt(9) + random.nextInt(9) + random.nextInt(9) + random.nextInt(9));
    }

    /**
     * 读取微信推送消息并包装成map对象
     * 
     * @param br
     * @return
     */
    public static Map<String, Object> tranXml(BufferedReader br) {
        String line = null;
        StringBuffer sb = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String xmlStr = sb.toString();
        log.info("接收微信推送消息的结果XML：" + xmlStr);

        map.put("xmlStr", xmlStr);
        if (!StringUtils.isEmpty(xmlStr)) {
            Document document = null;

            try {
                document = DocumentHelper.parseText(xmlStr);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            if (document == null) {
                return map;
            }

            Element root = document.getRootElement();
            String msgType = root.elementTextTrim("MsgType");
            map.put("toUserName", root.elementTextTrim("ToUserName"));
            map.put("fromUserName", root.elementTextTrim("FromUserName"));
            map.put("createTime", root.elementTextTrim("CreateTime"));
            map.put("msgType", msgType);
            if ("text".equals(msgType)) {
                map.put("intMsgType", 0);
                map.put("content", root.elementTextTrim("Content"));
                map.put("msgId", root.elementTextTrim("MsgId"));
            } else if ("image".equals(msgType)) {
                map.put("intMsgType", 1);
                map.put("picUrl", root.elementTextTrim("PicUrl"));
                map.put("MediaId", root.elementTextTrim("MediaId"));
                map.put("msgId", root.elementTextTrim("MsgId"));
            } else if ("voice".equals(msgType)) {
                map.put("intMsgType", 2);
                map.put("mediaId", root.elementTextTrim("MediaId"));
                map.put("format", root.elementTextTrim("Format"));
                map.put("msgId", root.elementTextTrim("MsgId"));
                map.put("Recognition", root.elementTextTrim("Recognition"));
            } else if ("video".equals(msgType)) {
                map.put("intMsgType", 3);
                map.put("mediaId", root.elementTextTrim("MediaId"));
                map.put("thumbMediaId", root.elementTextTrim("ThumbMediaId"));
                map.put("msgId", root.elementTextTrim("MsgId"));
            } else if ("location".equals(msgType)) {
                map.put("intMsgType", 4);
                map.put("locationX", root.elementTextTrim("Location_X"));
                map.put("locationY", root.elementTextTrim("Location_Y"));
                map.put("scale", root.elementTextTrim("Scale"));
                map.put("label", root.elementTextTrim("Label"));
                map.put("msgId", root.elementTextTrim("MsgId"));
            } else if ("link".equals(msgType)) {
                map.put("intMsgType", 5);
                map.put("title", root.elementTextTrim("Title"));
                map.put("description", root.elementTextTrim("Description"));
                map.put("url", root.elementTextTrim("Url"));
                map.put("msgId", root.elementTextTrim("MsgId"));
            } else if ("event".equals(msgType)) {
                String event = root.elementTextTrim("Event");
                map.put("event", event);
                String eventKey = root.elementTextTrim("EventKey");
                String ticket = root.elementTextTrim("Ticket");
                if ("subscribe".equals(event)) {
                    if (StringUtils.isEmpty(eventKey) || StringUtils.isEmpty(ticket)) {
                        map.put("intMsgType", 8);
                    } else {
                        map.put("intMsgType", 11);
                        map.put("eventKey", eventKey);
                        map.put("ticket", ticket);
                    }
                } else if ("unsubscribe".equals(event)) {
                    map.put("intMsgType", 9);
                } else if ("SCAN".equals(event)) {
                    map.put("intMsgType", 10);
                    map.put("eventKey", eventKey);
                    map.put("ticket", ticket);
                } else if ("LOCATION".equals(event)) {
                    map.put("intMsgType", 12);
                    map.put("latitude", root.elementTextTrim("Latitude"));
                    map.put("longitude", root.elementTextTrim("Longitude"));
                    map.put("precision", root.elementTextTrim("Precision"));
                } else if ("CLICK".equals(event)) {
                    map.put("intMsgType", 13);
                    map.put("eventKey", eventKey);
                } else if ("VIEW".equals(event)) {
                    map.put("intMsgType", 14);
                    map.put("eventKey", eventKey);
                }
            } else {
                map.put("error", 1);
            }
        }
        return map;
    }

    public static Integer getIntMsgType(String xmlStr) {
        if (!StringUtils.isEmpty(xmlStr)) {
            Document document = null;
            try {
                document = DocumentHelper.parseText(xmlStr);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            if (document == null) {
                return null;
            }
            Element root = document.getRootElement();
            String msgType = root.elementTextTrim("MsgType");
            if ("transfer_customer_service".equals(msgType)) {
                return 99;
            }
            if ("text".equals(msgType)) {
                return 0;
            } else if ("image".equals(msgType)) {
                return 1;
            } else if ("voice".equals(msgType)) {
                return 2;
            } else if ("video".equals(msgType)) {
                return 3;
            } else if ("location".equals(msgType)) {
                return 4;
            } else if ("link".equals(msgType)) {
                return 5;
            } else if ("music".equals(msgType)) {
                return 6;
            } else if ("news".equals(msgType)) {
                return 7;
            } else if ("event".equals(msgType)) {
                String event = root.elementTextTrim("Event");
                String eventKey = root.elementTextTrim("EventKey");
                String ticket = root.elementTextTrim("Ticket");
                if ("subscribe".equals(event)) {
                    if (StringUtils.isEmpty(eventKey) || StringUtils.isEmpty(ticket)) {
                        return 8;
                    } else {
                        return 11;
                    }
                } else if ("unsubscribe".equals(event)) {
                    return 9;
                } else if ("SCAN".equals(event)) {
                    return 10;
                } else if ("LOCATION".equals(event)) {
                    return 12;
                } else if ("CLICK".equals(event)) {
                    return 13;
                } else if ("VIEW".equals(event)) {
                    return 14;
                }
            } else {
                return null;
            }
        }
        return null;
    }

}
