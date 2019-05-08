package com.gaby.sms.model.permission.staff;

import com.gaby.model.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class Response extends BaseResponse {
    private List<Item> items;
}
