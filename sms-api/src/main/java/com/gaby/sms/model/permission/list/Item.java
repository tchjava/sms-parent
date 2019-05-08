package com.gaby.sms.model.permission.list;

import com.gaby.annotation.Field;
import lombok.Data;

import java.io.Serializable;
@Data
public class Item implements Serializable {
    @Field(comment = "权限标识")
    private Long id;
    @Field(comment = "权限名称")
    private String name;
    @Field(comment = "菜单url")
    private String url;
    @Field(comment = "权限类型 0-菜单 1-按钮")
    private Integer type;
    @Field(comment = "权限代码标识")
    private String percode;
    @Field(comment = "父节点")
    private Long parentId;
    @Field(comment = "排序")
    private Integer sort;

    public Long getParentId() {
        return parentId==null?-1:parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
