package com.gaby.sms.system;

import com.gaby.annotation.Field;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuInfo implements Serializable {
    @Field(comment = "菜单名称")
    private String name;
    @Field(comment = "菜单url")
    private String url;
    @Field(comment = "菜单图标")
    private String icon;
    @Field(comment ="父节点")
    private Long parentId;
    @Field(comment ="权限标识")
    private Long id;
    @Field(comment = "子菜单")
    private List<MenuInfo> subMenus;

    public Long getParentId() {
        return parentId!=null?parentId:-1;
    }
}
