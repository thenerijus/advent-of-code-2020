import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Part one of
 * https://adventofcode.com/2020/day/9
 *
 * @author Nerijus
 */
public class Day09_1 {

    public static final int PREAMBLE_LENGTH = 25;

    public static void main(String[] args) {
        System.out.println("First number not having this property: " + new Day09_1().getResult());
    }

    private long getResult() {
        return getNumberNotMatchingRule();
    }

    Long getNumberNotMatchingRule() {
        List<Long> numbers = getInputNumbers();
        for (int idx = 0; idx < numbers.size(); idx++) {
            if (idx < PREAMBLE_LENGTH) {
                continue;
            }
            Long numberToCheck = numbers.get(idx);
            // take all preable numbers
            List<Long> preableNumbers = collectPreableNumbersFrom(numbers, idx - 1);
            if (!hasSumEqualTo(preableNumbers, numberToCheck)) {
                // found it
                return numberToCheck;
            }
        }
        throw new IllegalStateException("Did not found required number");
    }

    List<Long> getInputNumbers() {
        return Inputs.readStrings("Day09").stream().map(Long::parseLong).collect(toList());
    }

    private boolean hasSumEqualTo(List<Long> preableNumbers, Long numberToCheck) {
        return preableNumbers.stream().anyMatch(first -> {
            ArrayList<Long> otherNumbers = new ArrayList<>(preableNumbers);
            otherNumbers.remove(first);
            return otherNumbers.stream().anyMatch(second -> first + second == numberToCheck);
        });
    }

    private List<Long> collectPreableNumbersFrom(List<Long> numbers, int index) {
        List<Long> preableNumbers = new ArrayList<>();
        for (int i = index; i > index - PREAMBLE_LENGTH; i--) {
            preableNumbers.add(numbers.get(i));
        }
        return preableNumbers;
    }
}
