package com.czh.controller;

import com.czh.App;
import com.czh.entity.Token;
import com.czh.entity.UserEntity;
import com.czh.exception.LoginException;
import com.czh.jwt.Header;
import com.czh.jwt.Payload;
import com.czh.model.LoginModel;
import com.czh.service.UserService;
import com.czh.util.Encrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody LoginModel user) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        UserEntity realUser = userService.getUser(user.getAccount());
        if (realUser == null) {
            System.out.println("用户不存在");
            throw new LoginException("用户不存在");
        }
        if (user.getPassword().equals(realUser.getPassword())) {
            String secret = App.SECRET;
//            System.out.println("secret="+secret);
            String iss = App.ISS;
//            System.out.println("iss="+iss);

            ObjectMapper mapper = new ObjectMapper();
            Header header = new Header();
            header.setAlg("JWT");
            header.setTyp("HS256");

            Payload payload = new Payload();
            payload.setIss(iss);
            payload.setAud(String.valueOf(realUser.getId()));
            payload.setExp(new Date().getTime() + 7200000);
            payload.setSub(user.getAccount());
            payload.setIat(new Date().getTime());

            String headerJson = mapper.writeValueAsString(header);
            String payloadJson = mapper.writeValueAsString(payload);
//            System.out.println("login payload = " + payloadJson );

            String base64Header = new String(Base64.encodeBase64(headerJson.getBytes()));
            String base64Payload = new String(Base64.encodeBase64(payloadJson.getBytes()));
            String signStr = base64Header + "." + base64Payload;
//            System.out.println(signStr);

            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            Encrypt encrypt = new Encrypt();
            String sign = encrypt.byte2hex(mac.doFinal(signStr.getBytes()));
            String token = signStr + "." + sign;
            return new Token(token, 7200);
        } else {
            System.out.println("密码错误");
            System.out.println(user);
            throw new LoginException("密码错误");
        }
    }


}
