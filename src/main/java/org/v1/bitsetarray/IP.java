package org.v1.bitsetarray;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class IP {
    private static final Pattern IP_REGEXP = Pattern.compile("(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");

    private Long a;
    private Long b;
    private Long c;
    private Long d;

    public IP(String ip) {
        String[] octetArray = ip.split("\\.");
        this.a = Long.parseLong(octetArray[0]);
        this.b = Long.parseLong(octetArray[1]);
        this.c = Long.parseLong(octetArray[2]);
        this.d = Long.parseLong(octetArray[3]);
    }


    public static boolean isIP(String ip) {
        return ip.matches(String.valueOf(IP_REGEXP));
    }

    public byte[] toByte(String address) throws UnknownHostException {
        return Inet4Address.getByName(address).getAddress();
    }

    public Long toLong() {
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    public String toString() {
        return a + "." + b + "." + c + "." + d;
    }

}
