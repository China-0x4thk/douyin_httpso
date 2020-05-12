package com.heyaug.httpso;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class NetUtils {



    private static final Pattern IPV4_PATTERN = Pattern.compile("^(" +

            "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}" +

            "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");



    private static boolean isIPv4Address(String input) {

        return IPV4_PATTERN.matcher(input).matches();

    }



    //获取本机IP地址

    public static InetAddress getLocalIPAddress() {

        Enumeration<NetworkInterface> enumeration = null;

        try {

            enumeration = NetworkInterface.getNetworkInterfaces();

        } catch (SocketException e) {

            e.printStackTrace();

        }

        if (enumeration != null) {

            while (enumeration.hasMoreElements()) {

                NetworkInterface nif = enumeration.nextElement();

                Enumeration<InetAddress> inetAddresses = nif.getInetAddresses();

                if (inetAddresses != null)

                    while (inetAddresses.hasMoreElements()) {

                        InetAddress inetAddress = inetAddresses.nextElement();

                        if (!inetAddress.isLoopbackAddress() && isIPv4Address(inetAddress.getHostAddress())) {

                            return inetAddress;

                        }

                    }

            }

        }

        return null;

    }
}