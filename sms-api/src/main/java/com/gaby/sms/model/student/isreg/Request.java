package com.gaby.sms.model.student.isreg;

import com.gaby.annotation.Field;
import com.gaby.model.BaseRequest;
import lombok.Data;

@Data
public class Request extends BaseRequest {
    @Field(comment = "帐号",nullable = false)
    private String account;
}
