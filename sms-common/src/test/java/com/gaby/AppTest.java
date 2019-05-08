package com.gaby;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void f1() throws UnsupportedEncodingException {
        String str = "鎵撳紑鏂伴〉闈�";
        System.out.println(new String(str.getBytes("gbk"),"utf-8"));
    }
}
