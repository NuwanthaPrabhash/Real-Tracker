package com.example.mainactivity.modal;

public class ProductDetails {

    private String productNameEdit;
    private Integer id;

    public ProductDetails() {
    }

    public ProductDetails(String productNameEdit, Integer id) {
        this.productNameEdit = productNameEdit;
        this.id = id;
    }

    public String getProductNameEdit() {
        return productNameEdit;
    }

    public void setProductNameEdit(String productNameEdit) {
        this.productNameEdit = productNameEdit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
