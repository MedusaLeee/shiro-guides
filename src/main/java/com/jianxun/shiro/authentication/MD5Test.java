package com.jianxun.shiro.authentication;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Test {
    public static void main(String[] args) {
        String source = "111111";
        String salt = "qwerty";
        int hashIterations = 1;
        // 构造方法中
        // 第一个参数：明文
        // 第二个参数：盐
        // 第三个参数：散列次数
        Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
        String password_md5 = md5Hash.toString();
        System.out.println("password_md5: " + password_md5);
        SimpleHash simpleHash = new SimpleHash("sha1", source, salt, hashIterations);
        String password_sha1 = simpleHash.toHex();
        System.out.println("password_sha1: " + password_sha1);
    }
}
