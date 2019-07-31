
package com.dayee.springboot.PO;

import java.util.Date;

public class User {

    private int     id;

    private int    age;

    private String name;

//    private String pwd;
    private Date create_time;

    private String phone;

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

//    public String getPwd() {
//
//        return pwd;
//    }
//
//    public void setPwd(String pwd) {
//
//        this.pwd = pwd;
//    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
