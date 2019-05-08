package com.gaby.sms.model.permission.pm.list;

import com.gaby.annotation.Field;
import com.gaby.model.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class Response extends BaseResponse {
    @Field(comment = "父级菜单")
    private List<Item> menus;
}
