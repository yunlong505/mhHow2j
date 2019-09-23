package com.how2java;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

public class DatabaseRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //能进入到这里，表示账号已经通过验证了
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //通过DAO获取角色和权限
        Set<String> permissions = new DAO().listPermissions(userName);
        Set<String> roles = new DAO().listRoles(userName);

        //授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(this.getCredentialsMatcher());
        //获取账号密码
        UsernamePasswordToken t = (UsernamePasswordToken) authenticationToken;
        String userName = t.getPrincipal().toString();
        //获取盐
        User user = new DAO().getUser(userName);
        String salt = user.getSalt();

        //获取数据库中的密码
        String passwordInDB = user.getPassword();
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        //盐也放进去
        //这样通过shiro.ini里配置的 HashedCredentialsMatcher 进行自动校验
        SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return a;

    }
    //直接写MD5校验的写法
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //获取账号密码
//        UsernamePasswordToken t = (UsernamePasswordToken) authenticationToken;
//        String userName = t.getPrincipal().toString();
//        String password = new String(t.getPassword());
//        //给密码加密
//        User user = new DAO().getUser(userName);
//        String salt = user.getSalt();
//        String passwordEncoded = new SimpleHash("md5",password,salt,2).toString();
//
//        //获取数据库中的密码
//        String passwordInDB = user.getPassword();
//        //如果为空就是账号不存在，如果不相同就是密码错误，但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
//        if (null == user || !passwordInDB.equals(passwordEncoded))
//            throw new AuthenticationException();
//
//        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
//        SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, password, getName());
//        return a;
//
//    }
}
