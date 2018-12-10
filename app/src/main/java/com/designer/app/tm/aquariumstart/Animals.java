package com.designer.app.tm.aquariumstart;

/**
 * Created by GTX on 2017-12-20.
 */

public class Animals {
    private int id;
    private String name;
    private String biology_name;
    private String quantity;
    private String total_price;
    private byte[] image;

    public Animals(String name, String biology_name, String quantity, String total_price, byte[] image, int id) {

        this.name = name;
        this.biology_name = biology_name;
        this.quantity = quantity;
        this.total_price = total_price;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiology_name() {
        return biology_name;
    }

    public void setBiology_name(String biology_name) {
        this.biology_name = biology_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
