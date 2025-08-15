package com.ksmJavaExam.JavaExam.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType addressType; // kötelező mező

    @Column(nullable = false)
    private String city; // kötelező mező

    @Column(nullable = false)
    private String zipcode; // kötelező mező

    private String line1; // opcionális mező

    public Address() {
    }

    public Address(AddressType addressType, String city, String zipcode, String line1) {
        this.addressType = addressType;
        this.city = city;
        this.zipcode = zipcode;
        this.line1 = line1;
    }

    public Long getId() {
        return id;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressType=" + addressType +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", line1='" + line1 + '\'' +
                '}';
    }
}