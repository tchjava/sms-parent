package com.gaby.sms.util;

import com.gaby.exception.BssException;

/**
*@discrption:身份类型的枚举
*@user:Gaby
*@createTime:2019-05-05 13:15
*/
public enum StaffTypeEnum {
    STUDENT(1L,"学生"),TEACHER(2L,"教师"),BAOAN(4L,"保安");
    private Long id;
    private String name;

    StaffTypeEnum(Long id, String name) {
        this.id=id;
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getEnum(Long id) {
        switch (id.intValue()) {
            case 1:
                return STUDENT.getName();
            case 2:
                return TEACHER.getName();
            case 4:
                return BAOAN.getName();
            default:
                throw new BssException("获取身份类型异常");
        }

    }
}
