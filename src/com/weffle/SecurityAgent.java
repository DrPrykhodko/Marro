package com.weffle;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * This abstract class for using static methods in the purpose of protection.
 *
 * @author Danylo Prykhodko
 */
public abstract class SecurityAgent {
    /**
     * Create random generated token.
     *
     * @param length Token length.
     * @return Token.
     */
    public static String createToken(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int rand = new Random().nextInt(62);
            String s = Integer.toString((rand > 35) ?
                    rand - 26 : rand, 36);
            stringBuilder.append((rand > 35) ? s.toUpperCase() : s);
        }
        return stringBuilder.toString();
    }

    /**
     * Encode String to MD5 hash.
     *
     * @param s String for encoding.
     * @return MD5 hash.
     */
    public static String encodeMD5(String s) {
        String hash = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(s.getBytes());
            hash = HexBin.encode(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
