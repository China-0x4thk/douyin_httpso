package com.yixia.utils;

public class TinyEncode {
    static {
        System.loadLibrary("te");
    }
    public TinyEncode() {
        super();
    }
    private static native String Decode(byte[] arg0);
    public static String DecodeResult(byte[] arg2) {
        String v0 = TinyEncode.Decode(arg2);
        if (v0.length() == 0) {
            v0 = new String(arg2);
        }
        return v0;
    }
}

