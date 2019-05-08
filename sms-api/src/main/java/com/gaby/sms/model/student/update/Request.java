package com.gaby.sms.model.student.update;

import com.gaby.annotation.Field;
import com.gaby.model.BaseRequest;
import lombok.Data;

@Data
public class Request extends BaseRequest {
    @Field(comment = "学生标识",nullable = false)
    private Long id;
    private String nickname;
    private String username;
    private String email;
    private String gender;
    private String status;
}
