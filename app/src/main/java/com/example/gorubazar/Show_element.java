package com.example.gorubazar;

public class Show_element {
    private String product_id;
    private String product_image;

    private String product_price;
    private String product_phone;
    private String description;
    private String product_type;


    public Show_element(String product_id, String product_image, String product_price, String product_phone, String description, String product_type) {
        this.product_id = product_id;
        this.product_image = product_image;
        this.product_price = product_price;
        this.product_phone = product_phone;
        this.description = description;
        this.product_type = product_type;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_phone() {
        return product_phone;
    }



    public String getDescription() {
        return description;
    }
}
