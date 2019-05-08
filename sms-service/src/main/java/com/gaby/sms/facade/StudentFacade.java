package com.gaby.sms.facade;

import com.gaby.model.BaseResponse;
import com.gaby.model.DefaultResponse;
import com.gaby.sms.model.student.query.Request;
import com.gaby.sms.model.student.query.Response;

public interface StudentFacade {
    Response query(Request request);

    Response regist(com.gaby.sms.model.student.add.Request request);

    DefaultResponse isReg(com.gaby.sms.model.student.isreg.Request request);

    DefaultResponse login(com.gaby.sms.model.student.login.Request request);

    BaseResponse list(com.gaby.sms.model.student.list.Request  request);

    com.gaby.sms.model.student.info.Response info(com.gaby.sms.model.student.info.Request request);

    DefaultResponse update(com.gaby.sms.model.student.update.Request request);

    DefaultResponse del(Long id);

    void repwd(Long id);
}
