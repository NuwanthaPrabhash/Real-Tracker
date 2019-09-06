package com.example.mainactivity.modal;

public class ProductDetails {

    private Integer id;
    private String productNameEdit;
    private Integer temperatureEdit;
    private Integer humidityEdit;
    private String fertilizerEdit;
    private String farmNameEdit;
    private String transportMediaEdit;
    private String weedingTypeEdit;


    public ProductDetails() {
    }

    public ProductDetails(Integer id, String productNameEdit, Integer temperatureEdit, Integer humidityEdit, String fertilizerEdit, String farmNameEdit, String transportMediaEdit, String weedingTypeEdit) {
        this.id = id;
        this.productNameEdit = productNameEdit;
        this.temperatureEdit = temperatureEdit;
        this.humidityEdit = humidityEdit;
        this.fertilizerEdit = fertilizerEdit;
        this.farmNameEdit = farmNameEdit;
        this.transportMediaEdit = transportMediaEdit;
        this.weedingTypeEdit = weedingTypeEdit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNameEdit() {
        return productNameEdit;
    }

    public void setProductNameEdit(String productNameEdit) {
        this.productNameEdit = productNameEdit;
    }

    public Integer getTemperatureEdit() {
        return temperatureEdit;
    }

    public void setTemperatureEdit(Integer temperatureEdit) {
        this.temperatureEdit = temperatureEdit;
    }

    public Integer getHumidityEdit() {
        return humidityEdit;
    }

    public void setHumidityEdit(Integer humidityEdit) {
        this.humidityEdit = humidityEdit;
    }

    public String getFertilizerEdit() {
        return fertilizerEdit;
    }

    public void setFertilizerEdit(String fertilizerEdit) {
        this.fertilizerEdit = fertilizerEdit;
    }

    public String getFarmNameEdit() {
        return farmNameEdit;
    }

    public void setFarmNameEdit(String farmNameEdit) {
        this.farmNameEdit = farmNameEdit;
    }

    public String getTransportMediaEdit() {
        return transportMediaEdit;
    }

    public void setTransportMediaEdit(String transportMediaEdit) {
        this.transportMediaEdit = transportMediaEdit;
    }

    public String getWeedingTypeEdit() {
        return weedingTypeEdit;
    }

    public void setWeedingTypeEdit(String weedingTypeEdit) {
        this.weedingTypeEdit = weedingTypeEdit;
    }
}
