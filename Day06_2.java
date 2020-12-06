import java.util.Arrays;
import java.util.Comparator;

/**
 * Part two of
 * https://adventofcode.com/2020/day/6
 *
 * @author Nerijus
 */
public class Day06_2 extends Day06_1 {
    public static void main(String[] args) {
        System.out.println("Sum of question counts (2): " + new Day06_2().getResult());
    }

    @Override
    long countQuestions(String group) {
        String[] people = group.split("\r\n");
        String leastAnswers = Arrays.stream(people).min(Comparator.comparingInt(String::length)).orElseThrow();

        return Arrays.stream(leastAnswers.split(""))
                .filter(a -> allContain(people, a))
                .count();
    }

    private boolean allContain(String[] people, String answer) {
        return Arrays.stream(people).allMatch(p -> p.contains(answer));
    }
}
