package com.douyin;

import android.annotation.SuppressLint;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author heyaug
 */
public class gorgon {
    private static final char[] charArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String getUrlParams(String url) {
        int v0 = url.indexOf("?");
        int v1 = url.indexOf("#");
        int v3 = -1;
        if (v0 == v3) {
            return null;
        }
        if (v1 == v3) {
            return url.substring(v0 + 1);
        }
        if (v1 < v0) {
            return null;
        }
        return url.substring(v0 + 1, v1);
    }

    private static String getSessionId(String cookie) {
        String[] v5 = cookie.replace(" ", "").split(",");
        int v0 = v5.length;
        int v1;
        for (v1 = 0; v1 < v0; ++v1) {
            String v2 = v5[v1];
            int v3 = v2.indexOf("sessionid=");
            if (v3 != -1) {
                return v2.substring(v3 + 10);
            }
        }
        return null;
    }

    public static String getGorgon(String url) {
        String urlParams = getUrlParams(url);
        String URL = encryptData(urlParams);
        long rticket = System.currentTimeMillis();
        if (url.contains("rticket")) {
            rticket = Long.parseLong(getTime(url));
        }
        int ks = Integer.parseInt(Long.toString(rticket / 1000));
        String X_SS_STUB = "00000000000000000000000000000000";
        String COOKIE = "00000000000000000000000000000000";
        String SESSION_ID = "00000000000000000000000000000000";

        byte[] leviathan = com.ss.sys.ces.a.leviathan(-1, ks, com.ss.a.b.a.ac(URL + X_SS_STUB + COOKIE + SESSION_ID));
        return com.ss.a.b.a.ab(leviathan);
    }


    public static String getGorgon(String url, HashMap<String, String> headers) {
        String urlParams = getUrlParams(url);
        String URL = encryptData(urlParams);
        long rticket = System.currentTimeMillis();
        if (url.contains("rticket")) {
            rticket = Long.parseLong(getTime(url));
        }
        int ks = Integer.parseInt(Long.toString(rticket / 1000));
        String X_SS_STUB = null;
        String COOKIE = null;
        String SESSION_ID = null;
        for (HashMap.Entry<String, String> iterator : headers.entrySet()) {
            if (iterator.getKey().toUpperCase().contains("X-SS-STUB")) {
                X_SS_STUB = iterator.getValue();
            }
            if (iterator.getKey().toUpperCase().contains("COOKIE")) {
                String cookie = iterator.getValue();
                if (cookie != null && cookie.length() > 0) {
                    COOKIE = encryptData(cookie);
                    String sessionId = getSessionId(cookie);
                    if (sessionId != null && sessionId.length() > 0) {
                        SESSION_ID = encryptData(sessionId);
                    }
                }
            }
        }
        if (URL == null || URL.length() <= 0) {
            URL = "00000000000000000000000000000000";
        }
        if (X_SS_STUB == null || X_SS_STUB.length() <= 0) {
            X_SS_STUB = "00000000000000000000000000000000";
        }
        if (COOKIE == null || COOKIE.length() <= 0) {
            COOKIE = "00000000000000000000000000000000";
        }
        if (SESSION_ID == null || SESSION_ID.length() <= 0) {
            SESSION_ID = "00000000000000000000000000000000";
        }
        return com.ss.a.b.a.ab(com.ss.sys.ces.a.leviathan(-1, ks, com.ss.a.b.a.ac(URL + X_SS_STUB + COOKIE + SESSION_ID)));
    }

    private static String getTime(String url) {
        Matcher matcher = Pattern.compile("^.*?rticket=(\\d+)&.*?").matcher(url);
        String result = null;
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    @SuppressLint("NewApi")
    private static String encryptData(String data) {
        if (data != null) {
            try {
                if (data.length() != 0) {
                    MessageDigest encrypt = MessageDigest.getInstance("MD5");
                    encrypt.update(data.getBytes(StandardCharsets.UTF_8));
                    byte[] md5Data = encrypt.digest();
                    return encryptMd5Data(md5Data, md5Data.length);
                }
            } catch (Exception exception) {
                return null;
            }
        }
        return null;
    }

    private static String encryptMd5Data(byte[] data, int dataLength) {
        if (data != null) {
            if (dataLength <= data.length) {
                int count = dataLength * 2;
                char[] v0 = new char[count];
                int v2 = 0;
                int v3 = 0;
                while (v2 < dataLength) {
                    int v4 = data[v2] & 0xFF;
                    int v5 = v3 + 1;
                    v0[v3] = charArray[v4 >> 4];
                    v3 = v5 + 1;
                    v0[v5] = charArray[v4 & 15];
                    ++v2;
                }
                return new String(v0, 0, count);
            }
            throw new IndexOutOfBoundsException();
        }
        throw new NullPointerException("bytes is null");
    }
}