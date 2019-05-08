package com.gaby.sms.model.student.list;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Item implements Serializable {

    private Integer id;
    private String account;
    private String nickname;
    private String username;
    private String email;
    private String gender;
    private Date createTime;
    private String status;
    private List<Long> roles;
}
