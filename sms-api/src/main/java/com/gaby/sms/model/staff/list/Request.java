package com.gaby.sms.model.staff.list;

import com.gaby.annotation.Field;
import com.gaby.model.BaseRequest;
import lombok.Data;

@Data
public class Request extends BaseRequest {
    @Field(comment = "关键字")
    private String keyword;
}
