package com.gaby.sms.mapper.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gaby.mybatis.auto.stu.entity.PermissionInfo;
import com.gaby.sms.model.permission.list.Item;
import com.gaby.sms.model.permission.list.Request;
import com.gaby.sms.system.MenuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PermissionDao extends BaseMapper<PermissionInfo> {
    List<Item> list(@Param("request") Request request);

    List<com.gaby.sms.model.permission.pm.list.Item> pm_list();

    PermissionInfo queryPermissionExceptSelf(@Param("request") com.gaby.sms.model.permission.update.Request request);

    Set<Long> selectListByPermissionId(@Param("id")Long id);

    List<Long> staff(@Param("roleId") Long roleId);

    List<MenuInfo> selectMenuList(@Param("account") String account);

    List<String> selectPermissionList(@Param("account") String account);

    List<String> queryPermissionAll();

    List<MenuInfo> queryMenuAll();
}
