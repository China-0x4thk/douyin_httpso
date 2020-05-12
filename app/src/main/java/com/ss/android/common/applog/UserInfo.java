package com.ss.android.common.applog;

public class UserInfo {
    static {
        try {
            System.loadLibrary("cms110");
        }
        catch(UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public UserInfo() {
        super();
    }

    public static native String a();

    public static native String getDescription();

    public static native String getFile();

    public static native String getFingerprint();

    public static native void getPackage(String arg0);

    public static native String getS();

    public static native byte[] getT();

    public static native int getTemperature();

    public static native int getType();

    public static native String getUserInfo(int arg0, String arg1, String[] arg2);

    public static native String getUserInfo(int arg0, String arg1, String[] arg2, String arg3);

    public static native String getUserInfo(int arg0, String[] arg1, String[] arg2, String arg3);

    public static native String getUserInfoSkipGet(int arg0, String arg1, String[] arg2);

    public static native int initUser(String arg0);

    public static native int isR();

    public static native void setAppId(int arg0);
}

