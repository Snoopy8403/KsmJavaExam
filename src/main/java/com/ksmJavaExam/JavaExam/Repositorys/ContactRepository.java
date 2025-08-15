package com.ksmJavaExam.JavaExam.Repositorys;

import com.ksmJavaExam.JavaExam.Models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
