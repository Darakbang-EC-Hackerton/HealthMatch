package com.darakthon.healthmatch.service;

import com.darakthon.healthmatch.domain.HealthProfile;
import com.darakthon.healthmatch.repository.HealthProfileRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HealthProfileServiceImpl implements HealthProfileService {
    private final HealthProfileRepository healthProfileRepository;

    @Transactional
    public Long save(HealthProfile healthProfile) {
        healthProfileRepository.save(healthProfile);
        return healthProfile.getId();
    }

    public List<HealthProfile> findAll() {
        return healthProfileRepository.findAll();
    }

    public HealthProfile findById(Long id) {
        return healthProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HealthProfile not found with ID: " + id));
    }

    @Transactional
    public void delete(HealthProfile healthProfile) {
        healthProfileRepository.delete(healthProfile);
    }

    @Transactional
    public void update(Long id, HealthProfile param) {
        HealthProfile healthProfile = healthProfileRepository.findById(id).get();
        healthProfile.updateProfile(param.getName(), param.getExerciseCount(), param.getWeight(), param.getHeight(),
                param.getSmokeCount());
    }
}
