import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Part one of
 * https://adventofcode.com/2020/day/4
 *
 * @author Nerijus
 */
public class Day04_1 {
    public static void main(String[] args) {
        System.out.println("Valid passport count: " + new Day04_1().getResult());
    }

    int getResult() {
        int validPassports = 0;
        for (String passport : getPassports()) {
            String[] fields = passport.replaceAll("\r\n", " ").split(" ");
            if (isValid(fields)) {
                validPassports = validPassports + 1;
            }
        }
        return validPassports;
    }

    private List<String> getPassports() {
        return Arrays.stream(Inputs.readString("Day04").split("\r\n\r\n"))
                .map(p -> p.replaceAll("\r\n", " "))
                .collect(toList());
    }

    boolean isValid(String[] fields) {
        return Arrays.stream(fields)
                .map(f -> f.substring(0, 3))
                .filter(f -> !"cid".equals(f))
                .count() == 7;
    }
}
