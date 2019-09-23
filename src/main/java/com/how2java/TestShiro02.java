package com.how2java;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiro02 {
    public static void main(String[] args) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //获得当前正在执行的subject
        Subject subject = SecurityUtils.getSubject();
        //获得shiro sessino实例
        Session session = subject.getSession();
        System.out.println("subject.getSession()"+session);
        //创建一个用户
        UsernamePasswordToken token = new UsernamePasswordToken("root","123");
        System.out.println("token"+token.toString());
        //是否记住用户
        token.setRememberMe(true);
        //登录
        try {
            subject.login(token);
            //没有抛异常则登录成功
            String currentUser = subject.getPrincipal().toString();
            System.out.println("当前登录的用户是："+currentUser);
            //判断用户是否是拥有某种角色
            boolean isRole = subject.hasRole( "productManager" );
            System.out.println("isRole:"+isRole);
            //是否拥有某种功能
            boolean isPer = subject.isPermitted("xiaoming:run");
            System.out.println("isPer:"+isPer);
            //退出登录
            subject.logout();
        } catch ( UnknownAccountException uae ) {
            System.out.println("用户名不存在");
        } catch ( IncorrectCredentialsException ice ) {
            System.out.println("密码错误");
        } catch ( LockedAccountException lae ) {
            System.out.println("用户被锁定，不能登录");
        } catch ( AuthenticationException ae ) {
            System.out.println("严重的错误");
        }

    }

}