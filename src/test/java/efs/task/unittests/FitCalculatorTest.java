package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenBMIisIncorrect(){
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean bmiResult = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(bmiResult);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenHeightIsZeroAndWeightAny(){
        //given
        double weight = 89.2;
        double height = 0.0;

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "{0} waga")
    @ValueSource(doubles = {82.1, 84.5, 88.7})
    void shouldReturnTrue_whenBMIisCorrect(double weight){
        double height = 1.77;
        boolean bmiResult = FitCalculator.isBMICorrect(weight, height);
        assertTrue(bmiResult);
    }

    @ParameterizedTest(name = "{0} waga, {1} wzrost")
    @CsvSource({"62.5,2.22", "42.5,1.96", "55.6,2.41"})
    void shouldReturnFalse_whenBMIisIncorrect_ForEachPairOfValuesFromCsvSource(double weight, double height) {
        //given
        //when
        boolean bmiResult = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(bmiResult);
    }

    @ParameterizedTest(name = "{0} waga, {1} wzrost")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenBMIisIncorrect_ForEachPairOfValuesFromCsvFileSource(double weight, double height) {
        //given
        //when
        boolean bmiResultCSV = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(bmiResultCSV);
    }

    @Test
    void shouldReturnUserWithTheWorstBMI(){
        //given
        double weight = 97.3;
        double height = 1.79;
        //when
        User user = FitCalculator.findUserWithTheWorstBMI(TestConstants.TEST_USERS_LIST);
        //then
        assertAll("Should return user with the worst BMI",
                () -> assertEquals(weight, user.getWeight()),
                () -> assertEquals(height, user.getHeight()
                ));
    }

    @Test
    void shouldReturnNull_ForEmptyList() {
        //given

        //when
        User user = FitCalculator.findUserWithTheWorstBMI(Collections.emptyList());
        //then
        assertNull(user);
    }

    @Test
    void shouldReturnExpectedBMIScoreForListOfUsers() {
        //given
        double[] UsersBmiResults;
        //when
        UsersBmiResults = FitCalculator.calculateBMIScore(TestConstants.TEST_USERS_LIST);
        //then
        assertArrayEquals(TestConstants.TEST_USERS_BMI_SCORE, UsersBmiResults);
    }
}

