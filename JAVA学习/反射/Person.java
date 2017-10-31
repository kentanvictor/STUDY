package com.dell.example.reflactDemo;

/**
 * Created by 20304 on 2017/10/31.
 */
public class Person {
    private Integer _number;
    private String name;
    private String address;

    public Integer get_number() {
        return _number;
    }

    public void set_number(Integer _number) {
        this._number = _number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
