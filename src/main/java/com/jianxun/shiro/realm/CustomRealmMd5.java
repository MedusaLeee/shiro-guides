package com.jianxun.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealmMd5 extends AuthorizingRealm {

    // 用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // token是用户输入的
        // 第一步：从token中取出用户信息
        String userCode = (String) token.getPrincipal();
        // 第二步：根据用户输入的userCode从数据库查询
        // 模拟数据库查询到的密码,散列值
        String password = "f3694f162729b7d0254c6e40260bf15c";
        // 盐值
        String salt = "qwerty";
        // 如果查询到返回认证信息AuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password, ByteSource.Util.bytes(salt), this.getName());
        return simpleAuthenticationInfo;
    }
    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
