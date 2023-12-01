package tut.dushyant.aoc;

import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tut.dushyant.aoc.helper.CalibrationHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        try {
            List<String> inputs = Resources.readLines(Resources.getResource("inputs.txt"), StandardCharsets.UTF_8);
            int out = inputs.parallelStream().map(
                    input -> new CalibrationHelper(input)
                            .getCalibration()).mapToInt(Integer::intValue
            ).sum();
            logger.info("Sum is {}",out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}