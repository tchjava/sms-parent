package com.gaby.sms.model.permission.distribute;

import com.gaby.annotation.Field;
import com.gaby.model.BaseRequest;
import lombok.Data;

import java.util.Set;

@Data
public class Request extends BaseRequest {
    @Field(comment = "角色标识",nullable = false)
    private Long roleId;
    @Field(comment = "权限标识")
    private Set<Long> ids;
}
