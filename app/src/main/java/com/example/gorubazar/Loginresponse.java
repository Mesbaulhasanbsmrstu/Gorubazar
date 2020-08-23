package com.example.gorubazar;



import android.icu.text.AlphabeticIndex;

public class Loginresponse {
    private String  message;
    private String id;

    private String phone;





    public Loginresponse(String message, String id, String phone)
    {
        this.message=message;
        this.id=id;
        this.phone=phone;

    }
    public String getMessage()
    {
        return message;
    }
    public String getId()
    {
        return id;
    }

    public String getPhone() {
        return phone;
    }

}

