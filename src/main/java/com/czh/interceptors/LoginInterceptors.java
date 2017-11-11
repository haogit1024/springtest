package com.czh.interceptors;

import com.czh.controller.LoginController;
import com.czh.jwt.Payload;
import com.czh.util.Encrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

public class LoginInterceptors implements HandlerInterceptor {
    private static final Logger log = Logger.getLogger(LoginInterceptors.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        log.info("interceptors is run");
        Encrypt encrypt = new Encrypt();
        Properties prop = encrypt.getProp();
        String authorization = httpServletRequest.getHeader("Authorization");
//        log.info("authorization = " + authorization);
        String token = authorization.split(" ")[1];
//        log.info("token = " + token);
        String[] tokenData = token.split("\\.");
        String base64Header = tokenData[0];
        String base64Payload = tokenData[1];
        String sign = tokenData[2];
        String signStrTpl = base64Header + "." + base64Payload;
        String secret = prop.getProperty("secret");
        String againSign = encrypt.hmacSHA256(signStrTpl, secret);
        if (!againSign.equals(sign)) {
            log.info("签名被篡改");
            //返回错误
            log.info("secret = " + secret);
            log.info("signStrTpl = " + signStrTpl);
            log.info("againSign = " + againSign);
            log.info("sign = " + sign);
            httpServletRequest.getRequestDispatcher("/error/SIGN_FAIL").forward(httpServletRequest,httpServletResponse);
            return false;
        }
        String payloadJson = encrypt.base64Decode(base64Payload);
//        log.info("payload = " + payloadJson);
        ObjectMapper mapper = new ObjectMapper();
        Payload payload = mapper.readValue(payloadJson, Payload.class);
        Long now = new Date().getTime();
        if (now > payload.getExp()) {
            log.debug("token已过期");
            //返回错误
            httpServletRequest.getRequestDispatcher("/error/SIGN_TIMEOUT").forward(httpServletRequest,httpServletResponse);
            return false;
        }
        //验证通过,将payload的aud(uid)保存在request中
        httpServletRequest.setAttribute("uid", payload.getAud());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
