package com.gaby.sms.model.permission.pm.list;

import lombok.Data;

import java.io.Serializable;

@Data
public class Item implements Serializable {
    private Long id;
    private String name;
}
