package org.v6_main.intarray;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class IP {
    private static final Pattern IP_REGEXP = Pattern.compile("(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");

    public static boolean isIP(String ip) throws IOException {
        if (ip.matches(String.valueOf(IP_REGEXP)))
            return true;
        else throw new IOException(String.format("This line - %s is not IP address",ip));
    }

    public static long toLongValue(String ipString) throws UnknownHostException {
        long result = 0;
        for (byte b : Inet4Address.getByName(ipString).getAddress())
            result = (result << 8) | (b & 255);
        return result;
    }

    public static Long toLong(String ip) {
        String[] octetArray = ip.split("\\.");
        int a = Integer.parseInt(octetArray[0]);
        int b = Integer.parseInt(octetArray[1]);
        int c = Integer.parseInt(octetArray[2]);
        int d = Integer.parseInt(octetArray[3]);
        return (((long) a << 24) | ((long) b << 16) | ((long) c << 8) | d);
    }

}
