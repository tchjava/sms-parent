package com.gaby.sms.model.student.add;

import com.gaby.annotation.Field;
import com.gaby.model.BaseRequest;
import lombok.Data;

import java.util.Set;

@Data
public class Request extends BaseRequest {
    @Field(comment ="真实姓名",nullable = false)
    private String username;
    @Field(comment = "帐号",nullable = false)
    private String account;
    @Field(comment = "邮箱",nullable = false)
    private String email;
    @Field(comment = "昵称",nullable = false)
    private String nickname;
    private String gender;
    @Field(comment = "身份类型标识",nullable = false)
    private Set<Long> staffTypeIds;
}
