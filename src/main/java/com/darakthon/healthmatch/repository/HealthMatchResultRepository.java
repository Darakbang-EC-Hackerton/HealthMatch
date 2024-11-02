package com.darakthon.healthmatch.repository;

import com.darakthon.healthmatch.domain.HealthMatchResult;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
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

    public Optional<HealthMatchResult> findById(Long id) {
        return Optional.ofNullable(em.find(HealthMatchResult.class, id));
    }

    public List<HealthMatchResult> findByProfileId(Long profileId) {
        return em.createQuery(
                        "select h from HealthMatchResult h where h.winnerProfile.id = :profileId or h.loserProfile.id = :profileId",
                        HealthMatchResult.class)
                .setParameter("profileId", profileId)
                .getResultList();
    }
}