package com.example.ztxs.myapplication2;

import android.app.Application;
import android.test.ApplicationTestCase;

import my.android.sample.JsonSample;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test() throws Exception{
        System.out.println("测试123");
        JsonSample.illustrateGson();
    }
}