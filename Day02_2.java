/**
 * Part two of
 * https://adventofcode.com/2020/day/2
 *
 * @author Nerijus
 */
public class Day02_2 extends Day02_1 {

    public static void main(String[] args) {
        System.out.println("Valid passwords: " + new Day02_2().getResult());
    }

    private long getResult() {
        return getPasswords()
                .stream()
                .filter(Day02_1.Password::matchesCriteriaTwo)
                .count();
    }
}
