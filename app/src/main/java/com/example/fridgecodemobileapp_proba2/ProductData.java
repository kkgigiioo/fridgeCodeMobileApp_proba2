package com.example.fridgecodemobileapp_proba2;

public class ProductData {

    private long barcode;
    private String expiration;
    private String name;
    private String brand;
    private String  locations;
    private int numberOfItem;
    private String unit;
    private String id;


    public ProductData() {

    }

    public ProductData(String id, long barcode, String expiration, String name, String brand, String locations, int numberOfItem, String unit) {
        this.id = id;
        this.barcode = barcode;
        this.expiration = expiration;
        this.name = name;
        this.brand = brand;
        this.locations = locations;
        this.numberOfItem = numberOfItem;
        this.unit = unit;
    }

    //getter
    public String getId() { return id;}

    public long getBarcode() {
        return barcode;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getLocations() {
        return locations;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public String getUnit() {
        return unit;
    }

    //setter
    public void setId(String id) {
        this.id = id;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public void setNumberOfItem(int numberOfItem) {
        this.numberOfItem = numberOfItem;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
