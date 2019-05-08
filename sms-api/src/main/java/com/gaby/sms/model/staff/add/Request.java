package com.gaby.sms.model.staff.add;

import com.gaby.annotation.Field;
import com.gaby.model.BaseRequest;
import lombok.Data;

@Data
public class Request extends BaseRequest {
    @Field(comment = "主键标识")
    private Long id;

    @Field(comment = "名称",nullable = false)
    private String name;
}
