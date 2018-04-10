package com.jianxun.shiro.authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

// 授权测试
public class AuthorizationTest {
    //角色授权、资源授权测试
    @Test
    public void testAuthorization() {
        // 创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        // 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        // 将securityManager设置到系统的运行环境中一般是单例
        SecurityUtils.setSecurityManager(securityManager);

        // 创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        // 创建subject
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        System.out.println("认证状态：" + subject.isAuthenticated());
        // 认证过后进行授权
        // 基于角色授权
        boolean isHasRole = subject.hasRole("role1");
        System.out.println("isHasRole: " + isHasRole);
        // 判断多个角色
        boolean isHasAllRole = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("isHasAllRole: " + isHasAllRole);

        // 使用check方法进行授权，如果授权不通过会抛出异常
        // subject.checkRole("role3");

        // 基于资源的授权
        // isPermitted传入权限标识符
        boolean isPermitted = subject.isPermitted("user:create");
        System.out.println("isPermitted: " + isPermitted);
        boolean isPermittedAll = subject.isPermittedAll("user:create", "user:delete");
        System.out.println("isPermittedAll: " + isPermittedAll);
        // 使用check方法进行授权，如果授权不通过会抛出异常
        // subject.checkPermission("user:delete11");
    }
}
