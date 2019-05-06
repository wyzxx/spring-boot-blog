package com.wyz.blog;

/**
 * @Author: wyz
 * @Date: 2019/5/5 19:43
 */
public class B implements A {

    @Override
    public void sing() {
        System.out.println(111);
    }
    public void test(){

    }

    public static void main(String[] args) {
        A a = new B();
        a.sing();
    }
}
