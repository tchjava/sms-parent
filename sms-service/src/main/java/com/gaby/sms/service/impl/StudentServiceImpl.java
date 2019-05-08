package com.gaby.sms.service.impl;

import com.gaby.mybatis.auto.stu.entity.UserAccountInfo;
import com.gaby.mybatis.base.service.impl.BaseServiceImpl;
import com.gaby.sms.mapper.dao.StudentDao;
import com.gaby.sms.model.student.info.Response;
import com.gaby.sms.model.student.list.Item;
import com.gaby.sms.service.StudentService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentDao, UserAccountInfo> implements StudentService {

    @Override
    public Page<Item> list(Map map) {
        return this.baseMapper.list(map);
    }

    @Override
    public Response info(Long id) {
        return this.baseMapper.info(id);
    }
}
