package com.example.demo.pick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickerPickRepository extends JpaRepository<PickerPick, Long> {
}