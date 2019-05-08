package com.gaby.sms.model.staff.list;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Item implements Serializable {
    private Long id;
    private String name;
    private Date createTime;
}
