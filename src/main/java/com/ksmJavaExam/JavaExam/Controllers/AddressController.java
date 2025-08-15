package com.ksmJavaExam.JavaExam.Controllers;

import com.ksmJavaExam.JavaExam.Models.Address;
import com.ksmJavaExam.JavaExam.Models.AddressType;
import com.ksmJavaExam.JavaExam.Models.Person;
import com.ksmJavaExam.JavaExam.Repositorys.AddressRepository;
import com.ksmJavaExam.JavaExam.Repositorys.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public AddressController(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    // GET - minden address
    @GetMapping("/findAll")
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    // GET - egy adott address
    @GetMapping("/findById/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        return addressRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - új address hozzárendelése person-hoz
    @PostMapping("/create/{personId}")
    public ResponseEntity<Address> create(@PathVariable Long personId, @RequestBody Address newAddress) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        boolean existsSameType = person.getAddresses().stream()
                .anyMatch(addr -> addr.getAddressType() == newAddress.getAddressType());
        if (existsSameType) {
            throw new RuntimeException("Az adott személynek már van ilyen típusú címe: " + newAddress.getAddressType());
        }

        person.getAddresses().add(newAddress);
        personRepository.save(person);

        return ResponseEntity.ok(newAddress);
    }

    // PUT - address szerkesztése
    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address updatedAddress) {
        return addressRepository.findById(id)
                .map(existing -> {
                    existing.setAddressType(updatedAddress.getAddressType());
                    existing.setCity(updatedAddress.getCity());
                    existing.setZipcode(updatedAddress.getZipcode());
                    existing.setLine1(updatedAddress.getLine1());
                    return ResponseEntity.ok(addressRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

/*    // DELETE - address törlése
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return addressRepository.findById(id)
                .map(address -> {
                    addressRepository.delete(address);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }*/
}
