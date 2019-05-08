package com.gaby.sms.model.staff.query.s;

import com.gaby.model.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class Response extends BaseResponse {
    private List<Long> roles;
}
