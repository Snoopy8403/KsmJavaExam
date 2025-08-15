package com.ksmJavaExam.JavaExam.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "person")
    public class Person {

        @Id
        @GeneratedValue
        private Long id;

        private String name;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "person_id")
        private List<Address> addresses = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "person_id")
        private List<Contact> contacts = new ArrayList<>();

        public Person() {
        }

        public Person(String name, List<Address> addresses, List<Contact> contacts) {
            this.name = name;
            this.addresses = addresses;
            this.contacts = contacts;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        public List<Contact> getContacts() {
            return contacts;
        }

        public void setContacts(List<Contact> contacts) {
            this.contacts = contacts;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", addresses=" + addresses +
                    ", contacts=" + contacts +
                    '}';
        }
    }