package com.gaby.sms.service;

import com.gaby.mybatis.auto.stu.entity.UserAccountInfo;
import com.gaby.mybatis.base.service.BaseService;
import com.gaby.sms.model.student.info.Response;
import com.gaby.sms.model.student.list.Item;
import com.github.pagehelper.Page;

import java.util.Map;

public interface StudentService extends BaseService<UserAccountInfo> {

    Page<Item> list(Map map);

    Response info(Long id);
}
