package com.ksmJavaExam.JavaExam;

import com.ksmJavaExam.JavaExam.Models.*;
import com.ksmJavaExam.JavaExam.Repositorys.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component

public class TestDataLoader implements CommandLineRunner {
    private PersonRepository personRepository;

    public TestDataLoader(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    public void run(String... args) throws Exception {
        // Első személy: 1 cím, 1 kontakt
        Address addr1 = new Address();
        addr1.setAddressType(AddressType.ADDRESS_TYPE_NORMAL);
        addr1.setCity("Budapest");
        addr1.setZipcode("1011");
        addr1.setLine1("Fő utca 1");

        Contact contact1 = new Contact();
        contact1.setContactType(ContactType.CONTACT_TYPE_EMAIL);
        contact1.setEmail("first.person@example.com");

        Person person1 = new Person();
        person1.setName("Első Személy");
        person1.setAddresses(List.of(addr1));
        person1.setContacts(List.of(contact1));

        // Második személy: 2 cím, 3 kontakt
        Address addr2 = new Address();
        addr2.setAddressType(AddressType.ADDRESS_TYPE_NORMAL);
        addr2.setCity("Debrecen");
        addr2.setZipcode("4024");
        addr2.setLine1("Kossuth tér 5");

        Address addr3 = new Address();
        addr3.setAddressType(AddressType.ADDRESS_TYPE_TEMPORARY);
        addr3.setCity("Szeged");
        addr3.setZipcode("6720");
        addr3.setLine1("Dóm tér 3");

        Contact contact2 = new Contact();
        contact2.setContactType(ContactType.CONTACT_TYPE_EMAIL);
        contact2.setEmail("second.person@example.com");

        Contact contact3 = new Contact();
        contact3.setContactType(ContactType.CONTACT_TYPE_MOBILE);
        contact3.setMobile("+36 20 123 4567");

        Contact contact4 = new Contact();
        contact4.setContactType(ContactType.CONTACT_TYPE_TELEPHONE);
        contact4.setTelephone("+36 1 234 5678");

        Person person2 = new Person();
        person2.setName("Második Személy");
        person2.setAddresses(Arrays.asList(addr2, addr3));
        person2.setContacts(Arrays.asList(contact2, contact3, contact4));

        // Mentés a repository-ba
        personRepository.saveAll(List.of(person1, person2));

        System.out.println("Inicializált 2 személy adat!");

    }
}
