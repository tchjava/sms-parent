package com.gaby.sms.model.staff.list;

import com.gaby.model.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class Response extends BaseResponse {
    List<Item> items;
}
