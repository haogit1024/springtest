package com.czh.controller;

import com.czh.entity.Token;
import com.czh.entity.User;
import com.czh.exception.LoginException;
import com.czh.jwt.Header;
import com.czh.jwt.Payload;
import com.czh.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public Token login(@RequestBody User user) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        User realUser = userService.getUser(user.getAccount());
        if (realUser == null) {
            throw new LoginException("用户不存在");
        }
        if (user.getPassword().equals(realUser.getPassword())) {
            Properties prop = new Properties();
            String propFileName = "/application.properties";
            InputStream is = LoginController.class.getResourceAsStream(propFileName);
            prop.load(is);
            String secret = prop.getProperty("secret");
            System.out.println(secret);
            String iss = prop.getProperty("iss");

            ObjectMapper mapper = new ObjectMapper();
            Header header = new Header();
            header.setAlg("JWT");
            header.setTyp("HS256");

            Payload payload = new Payload();
            payload.setIss(iss);
            payload.setAud(user.getAccount());
            payload.setExp(new Date().getTime() + 7200);
            payload.setSub(user.getAccount());
            payload.setExp(new Date().getTime());

            String headerJson = mapper.writeValueAsString(header);
            String payloadJson = mapper.writeValueAsString(payload);

            String base64Header = new String(Base64.encodeBase64(headerJson.getBytes()));
            String base64Payload = new String(Base64.encodeBase64(payloadJson.getBytes()));
            String signStr = base64Header + "." + base64Payload;
            System.out.println(signStr);

            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            String sign = byte2hex(mac.doFinal(signStr.getBytes()));

            return new Token(sign, 7200);
        } else {
            throw new LoginException("密码错误");
        }
    }

    private String base64Encode(String str) {
        byte[] bytes = Base64.encodeBase64(str.getBytes());
        return new String(bytes);
    }

    private String base64Decode(String str) {
        byte[] bytes = Base64.decodeBase64(str.getBytes());
        return new String(bytes);
    }

    private String HmacSHA256(String content, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
//        String sign = byte2hex(mac.doFinal(content.getBytes()));
        return byte2hex(mac.doFinal(content.getBytes()));
    }

    private static String byte2hex(byte[] b) {
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
