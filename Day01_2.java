import java.util.List;

/**
 * Part two of
 * https://adventofcode.com/2020/day/1
 *
 * @author Nerijus
 */
public class Day01_2 {
    public static void main(String[] args) {
        System.out.println("Multiplied: " + new Day01_2().getResult());
    }

    private int getResult() {
        List<Integer> entries = Inputs.readInts("Day01");
        for (Integer first : entries) {
            for (Integer second : entries) {
                for (Integer third : entries) {
                    if (first + second + third == 2020) {
                        System.out.printf("%d + %d + %d = 2020\n", first, second, third);
                        return first * second * third;
                    }
                }
            }
        }
        throw new IllegalStateException("Did not found entries that if added together results in 2020");
    }
}
