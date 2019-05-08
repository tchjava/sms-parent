package com.gaby.sms.service;

import com.gaby.mybatis.auto.stu.entity.PermissionInfo;
import com.gaby.mybatis.base.service.BaseService;
import com.gaby.sms.model.permission.list.Item;
import com.gaby.sms.model.permission.list.Request;
import com.gaby.sms.system.MenuInfo;

import java.util.List;
import java.util.Set;

public interface PermissionService extends BaseService<PermissionInfo> {
    List<Item> list(Request request);

    List<com.gaby.sms.model.permission.pm.list.Item> pm_list();

    PermissionInfo queryPermissionExceptSelf(com.gaby.sms.model.permission.update.Request request);

    Set<Long> selectListByPermissionId(Long id);

    List<Long> staff(Long roleId);

    List<MenuInfo> selectMenuList(String account);

    List<String> selectPermissionList(String account);

    List<String> queryPermissionAll();

    List<MenuInfo> queryMenuAll();
}
