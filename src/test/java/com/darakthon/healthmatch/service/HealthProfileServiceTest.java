package com.darakthon.healthmatch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.darakthon.healthmatch.domain.HealthProfile;
import com.darakthon.healthmatch.repository.HealthProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class HealthProfileServiceTest {
    @Autowired
    private HealthProfileService healthProfileService;
    @Autowired
    private HealthProfileRepository healthProfileRepository;

    @Test
    void 프로필_등록() throws Exception {
        // given
        HealthProfile healthProfile = getHealthProfile();
        // when
        Long profileId = healthProfileService.save(healthProfile);
        // then
        assertEquals(healthProfile, healthProfileRepository.findById(profileId).get());
    }

    @Test
    void 모두_조회() throws Exception {
        // given
        HealthProfile healthProfile = getHealthProfile();
        HealthProfile healthProfile2 = getHealthProfile2();
        // when
        Long profileId1 = healthProfileService.save(healthProfile);
        Long profileId2 = healthProfileService.save(healthProfile2);
        // then
        assertEquals(2, healthProfileService.findAll().size());
    }

    @Test
    void 프로필_수정() throws Exception {
        // given
        HealthProfile healthProfile = getHealthProfile();
        Long profileId = healthProfileService.save(healthProfile);
        HealthProfile param = HealthProfile.builder()
                .name("test3")
                .exerciseCount(3)
                .weight(70.0)
                .height(180.0)
                .smokeCount(3)
                .drinkCount(3)
                .build();
        // when
        healthProfileService.update(profileId, param);
        // then
        assertNotEquals(param, healthProfileRepository.findById(profileId).get());
        assertEquals("test3", healthProfileRepository.findById(profileId).get().getName());

    }

    @Test
    void 회원_삭제() throws Exception {
        // given
        HealthProfile healthProfile = getHealthProfile();
        Long profileId = healthProfileService.save(healthProfile);
        // when
        healthProfileService.delete(healthProfile);
        // then
        assertEquals(0, healthProfileService.findAll().size());
    }

    private HealthProfile getHealthProfile() {
        return HealthProfile.builder()
                .name("test")
                .exerciseCount(3)
                .weight(70.0)
                .height(180.0)
                .smokeCount(3)
                .drinkCount(3)
                .build();
    }

    private HealthProfile getHealthProfile2() {
        return HealthProfile.builder()
                .name("taeho")
                .exerciseCount(3)
                .weight(70.0)
                .height(180.0)
                .smokeCount(3)
                .drinkCount(3)
                .build();
    }
}