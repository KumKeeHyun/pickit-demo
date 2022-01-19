package com.example.demo.domain.pick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickRepository extends JpaRepository<Pick, PickId> {

    List<Pick> findByArticleIdInAndPickerId(List<Long> articleIds, Long pickerId);
}
