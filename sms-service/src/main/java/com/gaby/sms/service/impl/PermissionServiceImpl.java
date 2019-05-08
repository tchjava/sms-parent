package com.gaby.sms.service.impl;

import com.gaby.mybatis.auto.stu.entity.PermissionInfo;
import com.gaby.mybatis.base.service.impl.BaseServiceImpl;
import com.gaby.sms.mapper.dao.PermissionDao;
import com.gaby.sms.model.permission.list.Item;
import com.gaby.sms.model.permission.list.Request;
import com.gaby.sms.service.PermissionService;
import com.gaby.sms.system.MenuInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionDao, PermissionInfo> implements PermissionService {
    @Override
    public List<Item> list(Request request) {
        return this.baseMapper.list(request);
    }

    @Override
    public List<com.gaby.sms.model.permission.pm.list.Item> pm_list() {
        return this.baseMapper.pm_list();
    }

    @Override
    public PermissionInfo queryPermissionExceptSelf(com.gaby.sms.model.permission.update.Request request) {
        return this.baseMapper.queryPermissionExceptSelf(request);
    }

    @Override
    public Set<Long> selectListByPermissionId(Long id) {
        return this.baseMapper.selectListByPermissionId(id);
    }

    @Override
    public List<Long> staff(Long roleId) {
        return this.baseMapper.staff(roleId);
    }

    /**
     * 根据帐号拿到菜单集合
     * @param account
     * @return
     */
    @Override
    public List<MenuInfo> selectMenuList(String account) {
        List<MenuInfo> menuInfos=this.baseMapper.selectMenuList(account);
        return arrangeMenu(menuInfos);
    }

    /**
     * 根据帐号拿到权限集合
     * @param account
     * @return
     */
    @Override
    public List<String> selectPermissionList(String account) {
        return this.baseMapper.selectPermissionList(account);
    }

    @Override
    public List<String> queryPermissionAll() {
        return this.baseMapper.queryPermissionAll();
    }

    @Override
    public List<MenuInfo> queryMenuAll() {
        List<MenuInfo> menuInfos= this.baseMapper.queryMenuAll();
        return arrangeMenu(menuInfos);
    }

    /**
     * 排列菜单
     * @param menuInfos
     * @return
     */
    public List<MenuInfo> arrangeMenu(List<MenuInfo> menuInfos) {
        //创造一个副本
        List<MenuInfo> copy = new ArrayList<>(menuInfos);
        //筛选出顶层父级菜单  parentId为-1
        List<MenuInfo> parentInfos=menuInfos.stream().filter(x->x.getParentId().intValue()==-1).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(parentInfos)) {
            //排列
            tree(parentInfos, copy);
        }
        return parentInfos;
    }

    /**
     *  将菜单排列
     * @param parentInfos
     * @param copy
     */
    public void tree(List<MenuInfo> parentInfos, List<MenuInfo> copy) {
        if (CollectionUtils.isNotEmpty(parentInfos)) {
            copy.removeAll(parentInfos);
            if (copy.size() == 0) {
                return;
            }
            parentInfos.forEach(parentInfo->{
                List<MenuInfo> subMenus = copy.stream().filter(x -> x.getParentId().intValue() == parentInfo.getId().intValue()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(subMenus)) {
                    parentInfo.setSubMenus(subMenus);
                    tree(parentInfo.getSubMenus(),copy);
                }
            });
        }
    }
}
