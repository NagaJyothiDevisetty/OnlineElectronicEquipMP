package com.example.onlineelectronicequipment.model;

public class Products {
    private String productname,description,price;
    public Products()
    {
    }

    public Products(String productname, String description, String price) {
        this.productname = productname;
        this.description = description;
        this.price = price;
    }


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

