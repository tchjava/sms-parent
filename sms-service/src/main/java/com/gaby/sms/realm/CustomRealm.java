package com.gaby.sms.realm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gaby.mybatis.auto.stu.entity.UserAccountInfo;
import com.gaby.mybatis.auto.stu.service.UserAccountInfoService;
import com.gaby.sms.service.PermissionService;
import com.gaby.sms.system.MenuInfo;
import com.gaby.sms.system.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @discrption:自定义的realm，用来shiro认证
 * @user:Gaby
 * @createTime:2019-04-28 18:05
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserAccountInfoService userAccountInfoService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public void setName(String name) {
        super.setName("securityRealm");
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //得到帐号
        String account = (String) authenticationToken.getPrincipal();
        if (StringUtils.isEmpty(account)) {
            return null;
        }
        UserAccountInfo userAccountInfo = userAccountInfoService.selectOne(new EntityWrapper<UserAccountInfo>().eq(UserAccountInfo.ACCOUNT, account)
                .eq(UserAccountInfo.STATUS, "00A"));
        if (null == userAccountInfo) {
            return null;
        }
        SystemUser systemUser = new SystemUser();
        systemUser.setAccount(userAccountInfo.getAccount());
        systemUser.setUsername(userAccountInfo.getUsername());
        List<MenuInfo> menuInfos;
        List<String> percodes;
        //判断是否是超管
        if ("root".equals(account)) {
            menuInfos = permissionService.queryMenuAll();
            percodes = permissionService.queryPermissionAll();
        } else {
            //到数据库查询菜单数据,权限数据
            menuInfos = permissionService.selectMenuList(account);
            percodes = permissionService.selectPermissionList(account);
        }
        systemUser.setMenuInfos(menuInfos);
        systemUser.setPermissions(percodes);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(systemUser, userAccountInfo.getPwd(), this.getName());
        System.out.println(account+"----通过认证");
        return authenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SystemUser systemUser = (SystemUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String> percodes;
        //判断是否是超管
        if ("root".equals(systemUser.getAccount())) {
            percodes = permissionService.queryPermissionAll();
        } else {
            //到数据库查询菜单数据,权限数据
            percodes = permissionService.selectPermissionList(systemUser.getAccount());
        }
        systemUser.setPermissions(percodes);
        simpleAuthorizationInfo.addStringPermissions(percodes);
        return simpleAuthorizationInfo;
    }

    /**
     * 清除在线用户的所有授权缓存
     */
    public void clearCached() {
        Cache<Object, AuthorizationInfo> authorizationCache = getAuthorizationCache();
        if (null != authorizationCache) {
            for (Object key : authorizationCache.keys()) {
                System.out.println(key);
                authorizationCache.remove(key);
            }
        }
    }

    /**
     * 清除当前用户的授权缓存
     */
    public void clearCurrentCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
