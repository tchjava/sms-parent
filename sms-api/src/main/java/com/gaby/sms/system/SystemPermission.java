package com.gaby.sms.system;

import lombok.Data;

import java.io.Serializable;
/**
*@discrption:返回的权限对象
*@user:Gaby
*@createTime:2019-05-08 11:21
*/
@Data
public class SystemPermission implements Serializable {
    private String percode;
}
