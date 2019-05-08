package com.gaby.sms.controller;

import com.gaby.model.DefaultResponse;
import com.gaby.model.LayuiTableResponse;
import com.gaby.sms.facade.PermissionFacade;
import com.gaby.sms.model.permission.list.Item;
import com.gaby.sms.model.permission.list.Request;
import com.gaby.sms.model.permission.pm.list.Response;
import com.gaby.web.plugin.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionFacade permissionFacade;

    @RequestMapping("list")
    public LayuiTableResponse<Item> list(Request request) {
        return permissionFacade.list(request);
    }

    @RequestMapping("add")
    public DefaultResponse add(@RequestBody com.gaby.sms.model.permission.add.Request request) {
        permissionFacade.add(request);
        return null;
    }

    @RequestMapping("pm-list")
    public Response pm_list() {
        return permissionFacade.pm_list();
    }

    @RequestMapping("update")
    public DefaultResponse update(@RequestBody com.gaby.sms.model.permission.update.Request request) {
        permissionFacade.update(request);
        return null;
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    public DefaultResponse del(@PathVariable(value = "id") Long id) {
        permissionFacade.del(id);
        return null;
    }

    /**
     * 权限分配查看
     * @param roleId
     * @return
     */
    @RequestMapping("staff/{roleId}")
    public com.gaby.sms.model.permission.staff.Response staff(@PathVariable(value = "roleId") Long roleId) {
        return permissionFacade.staff(roleId);
    }

    @RequestMapping("distribute")
    public DefaultResponse distribute(@RequestBody com.gaby.sms.model.permission.distribute.Request request) {
        permissionFacade.distribute(request);
        return null;
    }

}
