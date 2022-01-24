package com.example.demo.domain.pick.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickRepository extends JpaRepository<Pick, PickId> {

    @Query("SELECT new com.example.demo.domain.pick.entity.ItemPickedCnt(p.itemId, COUNT(p.itemId)) " +
            "FROM Pick p " +
            "WHERE p.pickId.articleId = :articleId " +
            "GROUP BY p.itemId")
    List<ItemPickedCnt> findItemPickedCntByArticleId(@Param("articleId") Long articleId);
}
