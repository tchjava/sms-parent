package com.gaby.sms.model.student.info;

import com.gaby.model.BaseResponse;
import lombok.Data;

@Data
public class Response extends BaseResponse {
    private String account;
    private String gender;
    private String email;
    private String pwd;
    private String username;
    private String nickname;
}
