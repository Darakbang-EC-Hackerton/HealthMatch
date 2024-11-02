package com.darakthon.healthmatch.repository;

import com.darakthon.healthmatch.domain.HealthMatchResult;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HealthMatchResultRepository {
    private final EntityManager em;

    public void save(HealthMatchResult healthMatchResult) {
        if (healthMatchResult.getId() == null) {
            em.persist(healthMatchResult);
        } else {
            em.merge(healthMatchResult);
        }
    }

    public HealthMatchResult findOne(Long id) {
        return em.find(HealthMatchResult.class, id);
    }
}