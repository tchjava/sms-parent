package com.gaby.sms.system;

import com.gaby.model.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class SystemUser extends BaseResponse {
    private String account;
    private String username;
    private List<MenuInfo> menuInfos;
    private List<String> permissions;
}
