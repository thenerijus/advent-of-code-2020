import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Part one of
 * https://adventofcode.com/2020/day/6
 *
 * @author Nerijus
 */
public class Day06_1 {
    public static void main(String[] args) {
        System.out.println("Sum of question counts: " + new Day06_1().getResult());
    }

    long getResult() {
        String[] groups = Inputs.readString("Day06").split("\r\n\r\n");
        return Arrays.stream(groups)
                .mapToLong(this::countQuestions)
                .sum();
    }

    long countQuestions(String group) {
        String[] people = group.split("\r\n");
        Set<String> uniqueQuestions = new HashSet<>();
        Arrays.stream(people).forEach(p -> uniqueQuestions.addAll(Arrays.asList(p.split(""))));
        return uniqueQuestions.size();
    }
}
