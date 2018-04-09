package com.jianxun.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthenticationTest {
    @Test
    public void testLoginAndLogout() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
        // 创建securityManager
        SecurityManager securityManager = factory.getInstance();
        // 将securityManager设置当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 从SecurityUtils里创建一个subject
        Subject subject = SecurityUtils.getSubject();
        // 再认证提交前准备token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        // 执行提交认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        // 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过1：" + isAuthenticated);
        // 退出
        subject.logout();
        isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过2：" + isAuthenticated);
    }
    @Test
    public void testLoginAndLogoutWithError() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
        // 创建securityManager
        SecurityManager securityManager = factory.getInstance();
        // 将securityManager设置当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 从SecurityUtils里创建一个subject
        Subject subject = SecurityUtils.getSubject();
        // 再认证提交前准备token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "1111112");
        // 执行提交认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                System.out.println("账号错误");
            }
            if (e instanceof IncorrectCredentialsException) {
                System.out.println("密码错误");
            }
        }
        // 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过1：" + isAuthenticated);
        // 退出
        subject.logout();
        isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过2：" + isAuthenticated);
    }
    @Test
    public void testCustomRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        // 创建securityManager
        SecurityManager securityManager = factory.getInstance();
        // 将securityManager设置当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 从SecurityUtils里创建一个subject
        Subject subject = SecurityUtils.getSubject();
        // 再认证提交前准备token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        // 执行提交认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        // 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过1：" + isAuthenticated);
        // 退出
        subject.logout();
        isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过2：" + isAuthenticated);
    }
}
