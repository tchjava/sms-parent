package com.gaby.sms.controller;

import com.gaby.model.BaseResponse;
import com.gaby.model.DefaultResponse;
import com.gaby.sms.facade.StudentFacade;
import com.gaby.sms.model.student.query.Request;
import com.gaby.sms.model.student.query.Response;
import com.gaby.web.plugin.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private StudentFacade studentFacade;

    @RequestMapping("query")
    public Response query(@RequestBody Request request){
        return studentFacade.query(request);
    }

    @RequestMapping("regist")
    public Response regist(@RequestBody com.gaby.sms.model.student.add.Request request){
        return studentFacade.regist(request);
    }

    @RequestMapping("isreg")
    public DefaultResponse isReg(@RequestBody com.gaby.sms.model.student.isreg.Request request) {
        return studentFacade.isReg(request);
    }

    @RequestMapping("login")
    public DefaultResponse login(@RequestBody com.gaby.sms.model.student.login.Request request) {
        return studentFacade.login(request);
    }
    @RequestMapping("list")
    @RequiresPermissions("student:list")
    public BaseResponse list(@RequestBody com.gaby.sms.model.student.list.Request request) {
        return studentFacade.list(request);
    }

    @RequestMapping("info")
    public com.gaby.sms.model.student.info.Response info(@RequestBody com.gaby.sms.model.student.info.Request request) {
        return studentFacade.info(request);
    }

    @RequestMapping("update")
    @RequiresPermissions("student:update")
    public DefaultResponse update(@RequestBody com.gaby.sms.model.student.update.Request request) {
        return studentFacade.update(request);
    }

    @RequestMapping("del/{id}")
    @RequiresPermissions("student:del")
    public DefaultResponse del(@PathVariable("id") Long id) {
        return studentFacade.del(id);
    }

    @RequestMapping("repwd/{id}")
    public DefaultResponse repwd(@PathVariable("id") Long id) {
        studentFacade.repwd(id);
        return null;
    }
}