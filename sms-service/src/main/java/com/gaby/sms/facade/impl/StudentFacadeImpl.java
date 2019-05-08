package com.gaby.sms.facade.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gaby.exception.BssException;
import com.gaby.model.BaseResponse;
import com.gaby.model.DefaultResponse;
import com.gaby.model.LayuiTableResponse;
import com.gaby.mybatis.auto.stu.entity.UserAccountInfo;
import com.gaby.mybatis.auto.stu.entity.UserStaffInfo;
import com.gaby.mybatis.auto.stu.service.UserStaffInfoService;
import com.gaby.sms.facade.StudentFacade;
import com.gaby.sms.model.student.list.Item;
import com.gaby.sms.model.student.query.Request;
import com.gaby.sms.model.student.query.Response;
import com.gaby.sms.service.StudentService;
import com.gaby.sms.util.StaffTypeEnum;
import com.gaby.util.ModelDtoConvertUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentFacadeImpl implements StudentFacade {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserStaffInfoService userStaffInfoService;

    @Override
    public Response query(Request request) {
        Response response = new Response();
        return response;
    }

    @Override
    public Response regist(com.gaby.sms.model.student.add.Request request) {
        //判断帐号是否存在
        UserAccountInfo db_info = studentService.selectOne(new EntityWrapper<UserAccountInfo>().eq(UserAccountInfo.ACCOUNT, request.getAccount())
                .in(UserAccountInfo.STATUS, Arrays.asList("00A", "00B")));
        if (db_info != null) {
            throw new BssException("该帐号已被注册");
        }
        UserAccountInfo insert = ModelDtoConvertUtil.convertDTO2Model(request, UserAccountInfo.class);
        insert.setPwd("123456");
        insert.setStatus("00A");
        insert.setCreateTime(new Date());
        studentService.insertVal(insert);

        if (CollectionUtils.isNotEmpty(request.getStaffTypeIds())) {
            request.getStaffTypeIds().forEach(staffTypeId->{
                UserStaffInfo insertStaff = new UserStaffInfo();
                insertStaff.setUserId(insert.getId());
                insertStaff.setType(staffTypeId.intValue());
                insertStaff.setStatus("00A");
                insertStaff.setCreateTime(new Date());
                insertStaff.setName(insert.getUsername()+"-"+StaffTypeEnum.getEnum(staffTypeId));
                userStaffInfoService.insert(insertStaff);
            });

        }

        return null;
    }

    @Override
    public DefaultResponse isReg(com.gaby.sms.model.student.isreg.Request request) {
        //判断帐号是否已注册
        UserAccountInfo db_info = studentService.selectOne(new EntityWrapper<UserAccountInfo>().eq(UserAccountInfo.ACCOUNT, request.getAccount())
                .in(UserAccountInfo.STATUS, Arrays.asList("00A", "00B")));
        if (db_info != null) {
            throw new BssException("该帐号已被注册");
        }
        return null;
    }

    @Override
    public DefaultResponse login(com.gaby.sms.model.student.login.Request request) {
        //验证帐号是否存在
        UserAccountInfo db_info= studentService.selectOne(new EntityWrapper<UserAccountInfo>().eq(UserAccountInfo.ACCOUNT, request.getAccount())
                .eq(UserAccountInfo.STATUS, "00A"));
        if (db_info == null) {
            throw new BssException("帐号或密码错误");
        }

        //验证密码是否正确
        if (!db_info.getPwd().equals(request.getPwd())) {
            throw new BssException("帐号或密码错误");
        }
        return null;
    }

    @Override
    public BaseResponse list(com.gaby.sms.model.student.list.Request request) {
        LayuiTableResponse<Item> layuiTableResponse = new LayuiTableResponse();
        PageHelper.startPage(request.getPage(), request.getLimit());
        Map map = new HashMap<>();
        map.put("request",request);
        Page<Item> page = studentService.list(map);
        layuiTableResponse.setCount(page.getTotal());
        layuiTableResponse.setData(page.getResult());

        return layuiTableResponse;
    }

    @Override
    public com.gaby.sms.model.student.info.Response info(com.gaby.sms.model.student.info.Request request) {
        return studentService.info(request.getId());
    }

    @Override
    public DefaultResponse update(com.gaby.sms.model.student.update.Request request) {
        UserAccountInfo db_info = studentService.selectById(request.getId());
        if (db_info == null) {
            throw new BssException("学生信息有误");
        }
        UserAccountInfo update = ModelDtoConvertUtil.convertDTO2Model(request, UserAccountInfo.class);
        update.setModifyTime(new Date());
        studentService.updateById(update);
        return null;
    }

    @Override
    public DefaultResponse del(Long id) {
        if(null!=id){
            UserAccountInfo db_info = studentService.selectById(id);
            if (db_info == null) {
                throw new BssException("该学生信息不存在");
            }
            UserAccountInfo update = new UserAccountInfo();
            update.setId(id);
            update.setStatus("00C");
            studentService.updateById(update);

            UserStaffInfo userStaffInfo = new UserStaffInfo();
            userStaffInfo.setStatus("00Z");
            userStaffInfoService.update(userStaffInfo, new EntityWrapper<UserStaffInfo>().eq(UserStaffInfo.USER_ID, id));
            return null;
        }else{
            throw new BssException("参数错误");
        }
    }

    @Override
    public void repwd(Long id) {
        if(null!=id){
            UserAccountInfo userAccountInfo = studentService.selectById(id);
            if (null == userAccountInfo) {
                throw new BssException("该学生信息不存在");
            }
            UserAccountInfo update = new UserAccountInfo();
            update.setId(userAccountInfo.getId());
            update.setPwd("123456");
            studentService.updateById(update);
        }
    }

}
