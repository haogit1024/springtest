package com.czh.interceptors;

import com.czh.App;
import com.czh.entity.Error;
import com.czh.jwt.Payload;
import com.czh.util.Encrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class DownloadInterceptors implements HandlerInterceptor {
    private final Logger log = Logger.getLogger(DownloadInterceptors.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("download interceptors run");
        Encrypt encrypt = new Encrypt();
        ObjectMapper mapper = new ObjectMapper();
        String token = httpServletRequest.getParameter("token");
        if ("".equals(token) || null == token) {
            log.info("token");
            responseError(httpServletResponse, mapper, 401, "UNAUTHORIZED");
            return false;
        }
//        log.info("token = " + token);
        String[] tokenData = token.split("\\.");
        String base64Header = tokenData[0];
        String base64Payload = tokenData[1];
        String sign = tokenData[2];
        String signStrTpl = base64Header + "." + base64Payload;

        String payloadJson = encrypt.base64Decode(base64Payload);
//        log.info("payload = " + payloadJson);

        Payload payload = mapper.readValue(payloadJson, Payload.class);
        Long now = new Date().getTime();
        if (now > payload.getExp()) {
            log.debug("token已过期");
            //返回错误
            responseError(httpServletResponse, mapper, 406, "SIGN_TIMEOUT");
            return false;
        }

        String secret = App.SECRET;
//        log.info("secret = " + secret);
        String againSign = encrypt.hmacSHA256(signStrTpl, secret);
        if (!againSign.equals(sign)) {
            log.info("签名被篡改");
            log.info("secret = " + secret);
            log.info("signStrTpl = " + signStrTpl);
            log.info("againSign = " + againSign);
            log.info("sign = " + sign);
            //返回错误
            responseError(httpServletResponse, mapper, 406, "SIGN_FAIL");
            return true;
        }
        //验证通过,将payload的aud(uid)保存在request中
        String uid = payload.getAud();
        log.info("uid = " + uid);
        httpServletRequest.setAttribute("uid", uid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void responseError(HttpServletResponse response, ObjectMapper mapper,int status, String errorMsg) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(406);
        PrintWriter out = response.getWriter();
        String jsonResults = mapper.writeValueAsString(new Error(status, errorMsg));
        out.write(jsonResults);
        out.close();
    }
}
