package com.darakthon.healthmatch.service;

import com.darakthon.healthmatch.domain.HealthProfile;
import java.util.List;

public interface HealthProfileService {

    Long save(HealthProfile healthProfile);

    List<HealthProfile> findAll();

    HealthProfile findOne(Long id);

    void delete(HealthProfile healthProfile);

    void update(Long id, HealthProfile param);
}
