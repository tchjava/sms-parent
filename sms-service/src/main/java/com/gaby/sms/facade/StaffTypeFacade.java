package com.gaby.sms.facade;

import com.gaby.model.DefaultResponse;
import com.gaby.sms.model.staff.list.Request;
import com.gaby.sms.model.staff.list.Response;

public interface StaffTypeFacade {
    Response list(Request request);

    com.gaby.sms.model.staff.query.s.Response query_s(Long id);

    DefaultResponse add(com.gaby.sms.model.staff.add.Request request);

    void update(com.gaby.sms.model.staff.add.Request request);

    void del(Long id);
}
