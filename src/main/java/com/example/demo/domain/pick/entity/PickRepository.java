package com.example.demo.domain.pick.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickRepository extends JpaRepository<Pick, PickId> {
}
