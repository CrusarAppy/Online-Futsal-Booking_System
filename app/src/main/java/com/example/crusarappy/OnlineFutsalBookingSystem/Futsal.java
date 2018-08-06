package com.example.crusarappy.OnlineFutsalBookingSystem;

public class Futsal {
    private String name;
    private String address;
    private int image;
    private String contact;

    public Futsal(String name, String address, int image, String contact) {
        super();
        this.name = name;
        this.address = address;
        this.image = image;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public int getImage() {
        return image;
    }
    public String getContact() {
        return contact;
    }
}
