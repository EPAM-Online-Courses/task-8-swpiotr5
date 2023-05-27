package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {
    private Planner planner;
    @BeforeEach
    public void init() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "ActivityLevel={0}")
    @EnumSource(ActivityLevel.class)
    void shouldCheckCorrectnessOfCalculatedDailyCaloriesDemand(ActivityLevel activityLevel) {
        //given
        User user = TestConstants.TEST_USER;
        int caloriesDemand = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);
        //when
        int calculatedDemand = planner.calculateDailyCaloriesDemand(user, activityLevel);
        //then
        assertEquals(caloriesDemand, calculatedDemand);
    }
    @Test
    void shouldCheckCorrectnessOfDailyIntake() {
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake dailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;
        //when
        DailyIntake calculatedDailyIntake = planner.calculateDailyIntake(user);
        //then
        assertAll(
                () -> assertEquals(dailyIntake.getCalories(), calculatedDailyIntake.getCalories()),
                () -> assertEquals(dailyIntake.getProtein(), calculatedDailyIntake.getProtein()),
                () -> assertEquals(dailyIntake.getFat(), calculatedDailyIntake.getFat()),
                () -> assertEquals(dailyIntake.getCarbohydrate(), calculatedDailyIntake.getCarbohydrate())
        );
    }
}
