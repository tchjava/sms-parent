package com.gaby.sms.model.permission.staff;

import com.gaby.annotation.Field;
import lombok.Data;

import java.io.Serializable;
@Data
public class Item implements Serializable {
    @Field(comment = "权限名称 + 权限标识符")
    private String name;
    @Field(comment = "父节点id")
    private Long pId;
    @Field(comment = "自身节点")
    private Long id;
    @Field(comment = "是否勾选")
    private boolean checked;
    @Field(comment = "是否展开")
    private boolean open;
}
