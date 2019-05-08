package com.gaby.sms.service.impl;

import com.gaby.mybatis.auto.stu.entity.StaffTypeInfo;
import com.gaby.mybatis.base.service.impl.BaseServiceImpl;
import com.gaby.sms.mapper.dao.StaffTypeDao;
import com.gaby.sms.model.staff.list.Item;
import com.gaby.sms.model.staff.list.Request;
import com.gaby.sms.service.StaffTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffTypeServiceImpl extends BaseServiceImpl<StaffTypeDao, StaffTypeInfo> implements StaffTypeService {
    @Override
    public List<Item> list(Request request) {
        return this.baseMapper.list(request);
    }

    @Override
    public List<Long> query_s(Long id) {
        return this.baseMapper.query_s(id);
    }

    @Override
    public StaffTypeInfo selectStaffTypeInfoByExceptSelf(Long id, String name) {
        return this.baseMapper.selectStaffTypeInfoByExceptSelf(id,name);
    }
}

