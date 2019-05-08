package com.gaby.sms.controller;

import com.gaby.model.DefaultResponse;
import com.gaby.model.LayuiTableResponse;
import com.gaby.sms.facade.StaffTypeFacade;
import com.gaby.sms.model.staff.list.Item;
import com.gaby.sms.model.staff.list.Request;
import com.gaby.sms.model.staff.list.Response;
import com.gaby.web.plugin.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffTypeController extends BaseController {

    @Autowired
    private StaffTypeFacade staffTypeFacade;

    /**
     * 查询所有身份
     * @param request
     * @return
     */
    @RequestMapping("list")
    public Response list(@RequestBody Request request) {
        return staffTypeFacade.list(request);
    }

    /**
     * layui要求返回格式的json格式
     * @param request
     * @return
     */
    @RequestMapping("lay-list")
    public LayuiTableResponse<Item> lay_list(@RequestBody Request request){
        List<Item> items = staffTypeFacade.list(request).getItems();
        LayuiTableResponse response = new LayuiTableResponse();
        response.setData(items);
        response.setCount(items.size());
        return response;
    }

    /**
     * 根据用户标识查询身份标识集合
     * @param id
     * @return
     */
    @RequestMapping("query-s/{id}")
    public com.gaby.sms.model.staff.query.s.Response query_s(@PathVariable(value="id") Long id) {
        return staffTypeFacade.query_s(id);
    }

    /**
     * 添加角色名
     * @param request
     * @return
     */
    @RequestMapping("add")
    public DefaultResponse add(@RequestBody com.gaby.sms.model.staff.add.Request request) {
        return staffTypeFacade.add(request);
    }

    /**
     * 更新角色名
     * @param request
     * @return
     */
    @RequestMapping("update")
    public DefaultResponse update(@RequestBody com.gaby.sms.model.staff.add.Request request) {
         staffTypeFacade.update(request);
         return null;
    }

    @RequestMapping("del/{id}")
    public DefaultResponse del(@PathVariable("id") Long id) {
        staffTypeFacade.del(id);
        return null;
    }

}
