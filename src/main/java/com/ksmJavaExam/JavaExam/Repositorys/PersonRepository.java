package com.ksmJavaExam.JavaExam.Repositorys;

import com.ksmJavaExam.JavaExam.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
