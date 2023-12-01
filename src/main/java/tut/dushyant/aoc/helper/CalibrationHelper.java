package tut.dushyant.aoc.helper;

import java.util.Arrays;
import java.util.List;

public class CalibrationHelper {

    private final String inputString;

    public CalibrationHelper(String input) {
        this.inputString = input;
    }

    public int getCalibration() {
        List<Integer> numbers = new java.util.ArrayList<>(10);
        Arrays.stream(
                inputString.replaceAll("[^-?0-9]+", " ").trim().split(" ")
        ).mapToInt(Integer::parseInt).forEach(val -> numbers.addAll(getDigits(val)));
        return (numbers.size()==1)?numbers.get(0)*10+numbers.get(0):numbers.get(0)*10+numbers.get(numbers.size()-1);
    }

    private List<Integer> getDigits(int inp) {
        return Arrays.asList(Integer.toString(inp).chars()
                .map(Character::getNumericValue).boxed().toArray(Integer[]::new));

    }

}