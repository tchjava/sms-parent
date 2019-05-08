package com.gaby.sms.service;

import com.gaby.mybatis.auto.stu.entity.StaffTypeInfo;
import com.gaby.mybatis.base.service.BaseService;
import com.gaby.sms.model.staff.list.Item;
import com.gaby.sms.model.staff.list.Request;

import java.util.List;

public interface StaffTypeService extends BaseService<StaffTypeInfo> {
    List<Item> list(Request request);

    List<Long> query_s(Long id);

    StaffTypeInfo selectStaffTypeInfoByExceptSelf(Long id, String name);
}
