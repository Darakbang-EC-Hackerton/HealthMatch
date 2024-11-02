package com.darakthon.healthmatch.utils;

import com.darakthon.healthmatch.domain.HealthProfile;

public class IdealValueCalculator {

    public static final int IDEAL_SMOKE_COUNT = 0;
    public static final int IDEAL_EXERCISE_COUNT = 7;

    public static double calculateIdealWeight(double height) {
        return 22 * Math.pow(height / 100, 2);
    }

    public static double calculateBMI(Double weightKg, Double heightCm) {
        double heightMeters = heightCm / 100.0;
        return Math.round((weightKg / (heightMeters * heightMeters)) * 100.0) / 100.0;
    }

    public static String determineHealthGrade(double bmi, HealthProfile healthProfile) {
        if (bmi < 18.5) {
            return "B";
        } else if (bmi < 25) {
            return "A+";
        } else if (bmi < 30) {
            return "B+";
        } else {
            return "C";
        }
    }

}
