package com.gaby.sms.mapper.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gaby.mybatis.auto.stu.entity.UserAccountInfo;
import com.gaby.sms.model.student.info.Response;
import com.gaby.sms.model.student.list.Item;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface StudentDao extends BaseMapper<UserAccountInfo> {

    Page<Item> list(Map map);

    Response info(@Param("id") Long id);
}
