package com.gaby.sms.facade.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gaby.exception.BssException;
import com.gaby.model.LayuiTableResponse;
import com.gaby.mybatis.auto.stu.entity.PermissionInfo;
import com.gaby.mybatis.auto.stu.entity.StaffPermissionRela;
import com.gaby.mybatis.auto.stu.entity.StaffTypeInfo;
import com.gaby.mybatis.auto.stu.service.StaffPermissionRelaService;
import com.gaby.sms.facade.PermissionFacade;
import com.gaby.sms.model.permission.list.Item;
import com.gaby.sms.model.permission.list.Request;
import com.gaby.sms.model.permission.pm.list.Response;
import com.gaby.sms.realm.CustomRealm;
import com.gaby.sms.service.PermissionService;
import com.gaby.sms.service.StaffTypeService;
import com.gaby.util.ModelDtoConvertUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PermissionFacadeImpl implements PermissionFacade {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private StaffTypeService staffTypeService;
    @Autowired
    private StaffPermissionRelaService staffPermissionRelaService;
    @Autowired
    private CustomRealm customRealm;


    @Override
    public LayuiTableResponse<Item> list(Request request) {
        LayuiTableResponse response = new LayuiTableResponse();
        List<Item> list = permissionService.list(request);
        response.setData(list);
        response.setCount(list.size());
        return response;
    }

    @Override
    public void add(com.gaby.sms.model.permission.add.Request request) {
        if (request.getType().intValue() == 0) {
            if (request.getPercode() == null) {
                throw new BssException("权限标识符不允许为空");
            }
        }
        PermissionInfo insert = ModelDtoConvertUtil.convertDTO2Model(request, PermissionInfo.class);
        insert.setStatus("00A");
        insert.setCreateTime(new Date());
        if (request.getParentId() != -1) {
            PermissionInfo parent = permissionService.selectById(request.getParentId());
            if (parent == null) {
                throw new BssException("参数错误");
            }
            insert.setPath(parent.getPath()+parent.getId()+"/");
        }else{
            insert.setParentId(null);
            insert.setPath("/");
        }
        permissionService.insert(insert);
    }

    @Override
    public Response pm_list() {
        Response response = new Response();
        response.setMenus(permissionService.pm_list());
        return response;
    }

    @Override
    public void update(com.gaby.sms.model.permission.update.Request request) {
        if (request.getType()==1) {
            if (StringUtils.isEmpty(request.getPercode())) {
                throw new BssException("权限标识符不允许为空");
            }
        }
        PermissionInfo db_info= permissionService.queryPermissionExceptSelf(request);
        if (null != db_info) {
            throw new BssException("更新失败，查看是否与其他权限冲突");
        }
        PermissionInfo update = ModelDtoConvertUtil.convertDTO2Model(request, PermissionInfo.class);
        PermissionInfo parent = permissionService.selectById(request.getParentId());
        if (parent == null) {
            throw new BssException("参数错误");
        }
        update.setPath(parent.getPath()+parent.getId()+"/");
        permissionService.updateById(update);
    }

    @Override
    public void del(Long id) {
        PermissionInfo db_info = permissionService.selectById(id);
        if (null == db_info) {
            throw new BssException("权限标识不存在");
        }
        //查询该权限下的子级id
        Set<Long> permissionIds = permissionService.selectListByPermissionId(db_info.getId());
        permissionIds.add(db_info.getId());
        permissionService.deleteBatchIds(permissionIds);
    }

    @Override
    public com.gaby.sms.model.permission.staff.Response staff(Long roleId) {
        com.gaby.sms.model.permission.staff.Response response = new com.gaby.sms.model.permission.staff.Response();
        //todo 先查出所有的权限排序,后期应该用redis
        List<Item> list = permissionService.list(null);
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuffer sb = new StringBuffer();
            List<com.gaby.sms.model.permission.staff.Item> items = new ArrayList<>();
            list.forEach(x->{
                com.gaby.sms.model.permission.staff.Item item = new com.gaby.sms.model.permission.staff.Item();
                item.setId(x.getId());
                item.setOpen(true);
                item.setChecked(false);
                item.setPId(x.getParentId());
                sb.append(x.getName()).append(" ").append(x.getPercode()==null?"":x.getPercode());
                item.setName(sb.toString());
                items.add(item);
                sb.setLength(0);
            });
            //查询该角色对应的权限id
            List<Long> staffList = permissionService.staff(roleId);
            if (CollectionUtils.isNotEmpty(staffList)) {
                items.forEach(x->{
                    if (staffList.contains(x.getId())) {
                        x.setChecked(true);
                    }
                });
            }
            response.setItems(items);
        }
        return response;
    }

    @Override
    public void distribute(com.gaby.sms.model.permission.distribute.Request request) {
        StaffTypeInfo staffTypeInfo = staffTypeService.selectById(request.getRoleId());
        if (null == staffTypeInfo) {
            throw new BssException("该角色不存在");
        }
        List<StaffPermissionRela> staffPermissionRelas = staffPermissionRelaService.selectList(new EntityWrapper<StaffPermissionRela>()
                .eq(StaffPermissionRela.STAFF_TYPE_ID, request.getRoleId()).eq(StaffPermissionRela.STATUS,"00A"));
        //删除原先的
        if (CollectionUtils.isNotEmpty(staffPermissionRelas)) {
            staffPermissionRelas.forEach(staffPermissionRela -> {
                staffPermissionRelaService.deleteById(staffPermissionRela.getId());
            });
        }

        List<PermissionInfo> permissionInfos = permissionService.selectBatchIds(request.getIds());
        if (CollectionUtils.isNotEmpty(permissionInfos)) {
            permissionInfos.forEach(permissionInfo ->{
                StaffPermissionRela staffPermissionRela = new StaffPermissionRela();
                staffPermissionRela.setCreateTime(new Date());
                staffPermissionRela.setPiId(permissionInfo.getId());
                staffPermissionRela.setStaffTypeId(staffTypeInfo.getId());
                staffPermissionRela.setStatus("00A");
                staffPermissionRelaService.insert(staffPermissionRela);
            });
        }
        //清除缓存
        customRealm.clearCached();
    }
}
