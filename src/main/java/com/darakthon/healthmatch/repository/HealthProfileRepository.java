package com.darakthon.healthmatch.repository;

import com.darakthon.healthmatch.domain.HealthProfile;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HealthProfileRepository {
    private EntityManager em;

    public void save(HealthProfile healthProfile) {
        if (healthProfile.getId() == null) {
            em.persist(healthProfile);
        } else {
            em.merge(healthProfile);
        }
    }

    public HealthProfile findOne(Long id) {
        return em.find(HealthProfile.class, id);
    }

    public List<HealthProfile> findAll() {
        return em.createQuery("select h from HealthProfile h", HealthProfile.class).getResultList();
    }

    public void delete(HealthProfile healthProfile) {
        em.remove(healthProfile);
    }


}
