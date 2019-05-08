package com.gaby.sms.model.permission.add;

import com.gaby.annotation.Field;
import com.gaby.model.BaseRequest;
import lombok.Data;

@Data
public class Request extends BaseRequest {
    @Field(comment = "权限名称",nullable = false)
    private String name;
    @Field(comment = "菜单url")
    private String url;
    @Field(comment = "权限类型",nullable = false)
    private Integer type;
    @Field(comment = "父节点")
    private Long parentId;
    @Field(comment = "权限标识符")
    private String percode;
    @Field(comment = "排序",nullable = false)
    private Integer sort;
    @Field(comment = "菜单图标")
    private String icon;
}
