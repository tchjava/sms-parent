package com.gaby.sms.mapper.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gaby.mybatis.auto.stu.entity.StaffTypeInfo;
import com.gaby.sms.model.staff.list.Item;
import com.gaby.sms.model.staff.list.Request;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffTypeDao extends BaseMapper<StaffTypeInfo> {
    List<Item> list(@Param("request") Request request);

    List<Long> query_s(@Param("id") Long id);

    StaffTypeInfo selectStaffTypeInfoByExceptSelf(@Param("id") Long id, @Param("name") String name);
}
