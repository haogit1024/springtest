package com.czh.utiltest;

import org.junit.Test;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by czh on 17-6-9.
 */

public class UtilTest {

    @Test
    public void testUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println("uuid = " + uuid);
    }

    @Test
    public void testMD5(){
        try {
            File file = new File("/home/czh/gitclone/1.jpg");
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            System.out.println("文件md5值：" + bigInt.toString(16));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMD52(){
        try {
            Properties prop = new Properties();
            String propFileName = "/file.properties";
            InputStream in = UtilTest.class.getResourceAsStream(propFileName);
            if (in == null) {
                System.out.println("fuck");
                return;
            }
            prop.load(in);
            String domain = prop.getProperty("domain");
            System.out.println("domain = " + domain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTimestamp(){
        System.out.println(new Date().getTime());
    }

    @Test
    public void testString() {
        String a = null;
        System.out.println(a.equals("a"));
    }

    @Test
    public void testMine(){
        String name = "aaa.doc";
        String type = new MimetypesFileTypeMap().getContentType(name);
//        FileNameMap fileNameMap = URLConnection.getFileNameMap();
//        String type = fileNameMap.getContentTypeFor(name);//name:"aa.txt"

        System.out.println(type);
    }
}
