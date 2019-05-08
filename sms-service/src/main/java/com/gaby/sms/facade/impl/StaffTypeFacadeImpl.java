package com.gaby.sms.facade.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gaby.exception.BssException;
import com.gaby.model.DefaultResponse;
import com.gaby.mybatis.auto.stu.entity.StaffTypeInfo;
import com.gaby.sms.facade.StaffTypeFacade;
import com.gaby.sms.model.staff.list.Request;
import com.gaby.sms.model.staff.list.Response;
import com.gaby.sms.service.StaffTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StaffTypeFacadeImpl implements StaffTypeFacade {

    @Autowired
    private StaffTypeService staffTypeService;

    @Override
    public Response list(Request request) {
        Response response = new Response();
        response.setItems(staffTypeService.list(request));
        return response;
    }

    @Override
    public com.gaby.sms.model.staff.query.s.Response query_s(Long id) {
        com.gaby.sms.model.staff.query.s.Response response = new com.gaby.sms.model.staff.query.s.Response();
        response.setRoles(staffTypeService.query_s(id));
        return response;
    }

    @Override
    public DefaultResponse add(com.gaby.sms.model.staff.add.Request request) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new BssException("名称不允许为空");
        }
        StaffTypeInfo db_info=staffTypeService.selectOne(new EntityWrapper<StaffTypeInfo>().eq(StaffTypeInfo.NAME,request.getName())
        .eq(StaffTypeInfo.STATUS,"00A"));

        if (null != db_info) {
            throw new BssException("该角色名已存在");
        }
        StaffTypeInfo insert = new StaffTypeInfo();
        insert.setId(null);
        insert.setName(request.getName());
        insert.setCreateTime(new Date());
        insert.setStatus("00A");
        staffTypeService.insert(insert);
        return null;
    }

    @Override
    public void update(com.gaby.sms.model.staff.add.Request request) {
        if (null == request.getId()) {
            throw new BssException("参数异常");
        }
        StaffTypeInfo db_info = staffTypeService.selectById(request.getId());
        if (db_info == null) {
            throw new BssException("参数异常");
        }
        StaffTypeInfo name_info= staffTypeService.selectStaffTypeInfoByExceptSelf(db_info.getId(), request.getName());
        if (null != name_info) {
            throw new BssException("该角色名已存在");
        }
        StaffTypeInfo update = new StaffTypeInfo();
        update.setId(db_info.getId());
        update.setName(request.getName());
        staffTypeService.updateById(update);
    }

    @Override
    public void del(Long id) {
        staffTypeService.deleteById(id);
    }

}
