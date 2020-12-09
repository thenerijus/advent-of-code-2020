import java.util.ArrayList;
import java.util.List;

/**
 * Part two of
 * https://adventofcode.com/2020/day/9
 *
 * @author Nerijus
 */
public class Day09_2 extends Day09_1 {

    public static void main(String[] args) {
        System.out.println("Encryption weakness in XMAS-encrypted list: " + new Day09_2().getResult());
    }

    private long getResult() {
        Long target = getNumberNotMatchingRule();
        List<Long> numbers = getInputNumbers();

        for (int startIndex = 0; startIndex < numbers.size(); startIndex++) {
            long sum = 0;
            for (int i = startIndex; i < numbers.size(); i++) {
                sum = sum + numbers.get(i);
                if (sum > target) {
                    break;
                }
                if (target.equals(sum)) {
                    // found it
                    return sumMinAndMaxInRange(numbers, startIndex, i);
                }
            }
        }

        throw new IllegalStateException("Did not found matching range");
    }

    private long sumMinAndMaxInRange(List<Long> numbers, int startIndex, int endIndex) {
        List<Long> numbersInRange = new ArrayList<>();
        for (int i = startIndex; i <= endIndex; i++) {
            numbersInRange.add(numbers.get(i));
        }
        return numbersInRange.stream().mapToLong(Long::longValue).min().orElseThrow()
                + numbersInRange.stream().mapToLong(Long::longValue).max().orElseThrow();
    }
}
