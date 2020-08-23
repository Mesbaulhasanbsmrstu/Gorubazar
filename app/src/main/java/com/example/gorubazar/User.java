package com.example.gorubazar;

public class User {
    String id;
    String phone;




    public User(String id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return Integer.parseInt(id);
    }

    public void setId(String id) {
        this.id = id;
    }

}

