package com.czh.util;

import com.czh.controller.LoginController;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * 加密工具类
 */
public class Encrypt {

//    public Properties getProp() throws IOException {
//        Properties prop = new Properties();
//        String propFileName = "/application.properties";
//        InputStream is = Encrypt.class.getResourceAsStream(propFileName);
//        prop.load(is);
//        return prop;
//    }

    public String base64Encode(String str) {
        byte[] bytes = Base64.encodeBase64(str.getBytes());
        return new String(bytes);
    }

    public String base64Decode(String str) {
        byte[] bytes = Base64.decodeBase64(str.getBytes());
        return new String(bytes);
    }

    public String hmacSHA256(String content, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
//        String sign = byte2hex(mac.doFinal(content.getBytes()));
        return byte2hex(mac.doFinal(content.getBytes()));
    }

    public String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}
