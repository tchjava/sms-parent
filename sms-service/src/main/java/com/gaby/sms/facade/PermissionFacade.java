package com.gaby.sms.facade;

import com.gaby.model.LayuiTableResponse;
import com.gaby.sms.model.permission.list.Item;
import com.gaby.sms.model.permission.list.Request;
import com.gaby.sms.model.permission.pm.list.Response;

public interface PermissionFacade{
    LayuiTableResponse<Item> list(Request request);

    void add(com.gaby.sms.model.permission.add.Request request);

    Response pm_list();

    void update(com.gaby.sms.model.permission.update.Request request);

    void del(Long id);

    com.gaby.sms.model.permission.staff.Response staff(Long roleId);

    void distribute(com.gaby.sms.model.permission.distribute.Request request);
}
