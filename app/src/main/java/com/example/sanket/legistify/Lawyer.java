package com.example.sanket.legistify;

/**
 * Created by Sanket on 09-10-2015.
 */
public class Lawyer {

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String name;
    String address;

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    String phone;
    String field;
}
