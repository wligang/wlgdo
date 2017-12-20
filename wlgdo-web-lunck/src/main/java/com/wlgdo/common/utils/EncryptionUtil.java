package com.wlgdo.common.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptionUtil {

    private static final BASE64Encoder base64en = new BASE64Encoder();

    private static final BASE64Decoder base64De = new BASE64Decoder();
    public static final String DES_KEY = "Jo6#asjsPU#&1WsPmsusjs8isj";

    /**
     * Base64编码.
     */
    public static String encodeBase64(String str) {
        return Base64.encodeBase64String(str.getBytes());
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符'+'�?/'转为'-'�?_', 见RFC3548).
     */
    public static String encodeUrlSafeBase64(String str) {
        return Base64.encodeBase64URLSafeString(str.getBytes());
    }

    /**
     * Base64解码.
     */
    public static String decodeBase64(String input) {
        return String.valueOf(Base64.decodeBase64(input));
    }

    /**
     * 加密String明文输入,String密文输出
     */
    public static String DESEncode(String src) {

        return DESEncode(src, DES_KEY);
    }

    /**
     * 加密String明文输入,String密文输出
     */
    public static String DESEncode(String src, String key) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        try {
            byteMing = src.getBytes();
            byteMi = DESEncodeByte(byteMing, key);
            strMi = base64en.encode(byteMi);
            strMi = strMi.replaceAll("\r", "@");
            strMi = strMi.replaceAll("\n", "\\!");
            strMi = strMi.replaceAll("\\+", ".");
        } catch (Exception e) {
            strMi = null;
        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }

    /**
     * 解密 以String密文输入,String明文输出
     * 
     * @param src
     * @return
     */
    public static String DESDecode(String src, String key) {
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try {
            String body = src.replaceAll("@", "\r");
            body = body.replaceAll("\\!", "\n");
            body = body.replaceAll("\\.", "\\+");
            byteMi = base64De.decodeBuffer(body);
            byteMing = DESDecodeByte(byteMi, key);
            strMing = new String(byteMing);
        } catch (Exception e) {
            strMing = null;
        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }

    public static String MD5Encode(String s) {
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
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 随机生成数字字母
     * 
     * @param length
     *            生成位数
     * @return
     */
    public static String getCharAndNumr(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) { // 字符串
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val.append((char) (choice + random.nextInt(26)));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val.append(String.valueOf(random.nextInt(10)));
            }
        }

        return val.toString();
    }

    /**
     * 随机生成数字字母
     * 
     * @param length
     *            生成位数
     * @return
     */
    public static String getCharOrNumr(int length, boolean isNum) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            if (isNum) { // 数字
                val.append(String.valueOf(random.nextInt(10)));
            } else { // 字符串
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val.append((char) (choice + random.nextInt(26)));
            }

        }

        return val.toString();
    }

    /**
     * 根据uuid生成32为长度唯一编码
     * 
     */
    public static String getUniqueChar() {
        String code = UUID.randomUUID().toString();
        return code.replaceAll("-", "");
    }

    /**
     * 根据参数生成KEY
     * 
     * @throws Exception
     */
    private static Key createKey(String strKey) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(strKey.getBytes());
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance("DES");
        } catch (Exception e) {
        }
        kg.init(secureRandom);

        return kg.generateKey();
    }

    /**
     * 加密以byte[]明文输入,byte[]密文输出
     * 
     * @param byteS
     *            @return @throws Exception @throws
     */
    private static byte[] DESEncodeByte(byte[] byteS, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, createKey(key));
        byte[] byteFina = cipher.doFinal(byteS);
        return byteFina;
    }

    /**
     * sha1加密算法
     * 
     * @param encryptText
     * @param encryptText
     * @return
     */
    public static String sha1Encrypt(String encryptText) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(encryptText.getBytes());
            byte[] digest = md.digest();
            return bytes2Hex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return encryptText;
        }
    }

    /**
     * 二行制转字符串
     * 
     * @param bytes
     * @return
     */
    public static String bytes2Hex(byte[] bytes) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bytes.length; i++) {
            tmp = (Integer.toHexString(bytes[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 解密以byte[]密文输入,以byte[]明文输出
     * 
     * @param byteD
     *            @return @throws Exception @throws
     */
    private static byte[] DESDecodeByte(byte[] byteD, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, createKey(key));
        byte[] byteFina = cipher.doFinal(byteD);
        return byteFina;
    }

    // public static void main(String args[]) {
    // String actBody = EncryptionUtil.DESEncode("{userId=12, orgId=10}",
    // "402881e844d549bf00000144d549bfc700000000");
    // System.out.println(actBody);
    //
    // String str2 = EncryptionUtil.DESDecode(actBody,
    // "402881e844d549bf00000144d549bfc700000000");
    // System.out.println(str2);
    // }

    public static void mainTest(String args[]) {
        String actBody = EncryptionUtil.DESEncode("{userId=2, orgId=1}", "402881e844d549bf00000144d549bfc700000000");
        System.out.println(actBody);

        String str2 = EncryptionUtil.DESDecode("13qG/4c7qpUU35UwUU7.MEXq8lSf5jBC", "402881e844d549bf00000144d549bfc700000000");
        System.out.println(str2);
    }
}
