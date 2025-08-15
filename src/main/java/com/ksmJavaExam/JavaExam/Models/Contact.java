package com.ksmJavaExam.JavaExam.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactType contactType; // kötelező mező

    private String email; // opcionális
    private String telephone; // opcionális
    private String mobile; // opcionális

    public Contact() {
    }

    public Contact(ContactType contactType, String email, String telephone, String mobile) {
        this.contactType = contactType;
        this.email = email;
        this.telephone = telephone;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactType=" + contactType +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}