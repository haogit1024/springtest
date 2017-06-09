package com.czh.utiltest;

import org.junit.Test;

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
}
