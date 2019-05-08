package com.gaby.sms.model.student.list;

import com.gaby.model.LayuiTableRequest;
import lombok.Data;

@Data
public class Request extends LayuiTableRequest {
    private String nickname;
    private String username;
    private String status;
    private String keyword;
}
