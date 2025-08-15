package com.ksmJavaExam.JavaExam.Repositorys;

import com.ksmJavaExam.JavaExam.Models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
