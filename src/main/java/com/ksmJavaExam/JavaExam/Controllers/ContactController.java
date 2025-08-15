package com.ksmJavaExam.JavaExam.Controllers;

import com.ksmJavaExam.JavaExam.Models.Contact;
import com.ksmJavaExam.JavaExam.Models.ContactType;
import com.ksmJavaExam.JavaExam.Models.Person;
import com.ksmJavaExam.JavaExam.Repositorys.ContactRepository;
import com.ksmJavaExam.JavaExam.Repositorys.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactRepository contactRepository;
    private final PersonRepository personRepository;

    public ContactController(ContactRepository contactRepository, PersonRepository personRepository) {
        this.contactRepository = contactRepository;
        this.personRepository = personRepository;
    }

    // GET - minden contact
    @GetMapping("/findAll")
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    // GET - egy adott contact
    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id) {
        return contactRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - új contact hozzárendelése person-hoz
    @PostMapping("/create/{personId}")
    public ResponseEntity<Contact> create(@PathVariable Long personId, @RequestBody Contact newContact) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        boolean existsSameType = person.getContacts().stream()
                .anyMatch(c -> c.getContactType() == newContact.getContactType());
        if (existsSameType) {
            throw new RuntimeException("Az adott személynek már van ilyen típusú kontaktja: " + newContact.getContactType());
        }

        person.getContacts().add(newContact);
        personRepository.save(person);

        return ResponseEntity.ok(newContact);
    }

    // PUT - contact szerkesztése
    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable Long id, @RequestBody Contact updatedContact) {
        return contactRepository.findById(id)
                .map(existing -> {
                    existing.setContactType(updatedContact.getContactType());
                    existing.setEmail(updatedContact.getEmail());
                    existing.setTelephone(updatedContact.getTelephone());
                    existing.setMobile(updatedContact.getMobile());
                    return ResponseEntity.ok(contactRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

/*    // DELETE - contact törlése
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contactRepository.delete(contact);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }*/
}
