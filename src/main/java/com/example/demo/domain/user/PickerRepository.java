package com.example.demo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PickerRepository extends JpaRepository<Picker, Long> {

    Optional<Picker> findByName(String name);
}
