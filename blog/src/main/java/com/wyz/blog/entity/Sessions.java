package com.wyz.blog.entity;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wyz
 * @Date: 2019/5/7 22:48
 */
public class Sessions {
    private static Map<String,String> map = new ConcurrentHashMap<>();

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public static Map<String, String> getMap() {
        return map;
    }
}
