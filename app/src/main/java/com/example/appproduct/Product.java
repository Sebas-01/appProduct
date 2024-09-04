package com.example.appproduct;

public class Product {
    private String reference, description;
    private  int price, typeRef;

    public Product(){

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    //----------------------------------------------------

    public int getTypeRef() {
        return typeRef;
    }

    public void setTypeRef(int typeRef) {
        this.typeRef = typeRef;
    }

    //----------------------------------------------------

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //----------------------------------------------------

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
