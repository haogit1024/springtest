package com.czh.controller;

import com.czh.App;
import com.czh.entity.Error;
import com.czh.entity.Token;
import com.czh.entity.User;
import com.czh.exception.LoginException;
import com.czh.jwt.Header;
import com.czh.jwt.Payload;
import com.czh.model.LoginModel;
import com.czh.service.UserService;
import com.czh.util.Encrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
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
    public Token login(@RequestBody LoginModel user) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        User realUser = userService.getUser(user.getAccount());
        if (realUser == null) {
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
            throw new LoginException("密码错误");
        }
    }


    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error loginError(LoginException e){
        return new Error(400, e.getMsg());
    }
}
