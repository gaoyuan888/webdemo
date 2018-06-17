package com.yyz.domain;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-06-17
 */
public class User {
    private Integer id;

    /**
     * �û�����
     */
    private String username;

    /**
     * ����
     */
    private Date birthday;

    /**
     * �Ա�
     */
    private String sex;

    /**
     * ��ַ
     */
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}