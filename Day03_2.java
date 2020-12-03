/**
 * Part two of
 * https://adventofcode.com/2020/day/3
 *
 * @author Nerijus
 */
public class Day03_2 extends Day03_1 {
    public static void main(String[] args) {
        System.out.println("Tree count multiplied: " + new Day03_2().getResult());
    }

    private long getResult() {
        return getTreeCount(new Rule(1, 1))
                * getTreeCount(new Rule(3, 1))
                * getTreeCount(new Rule(5, 1))
                * getTreeCount(new Rule(7, 1))
                * getTreeCount(new Rule(1, 2));
    }
}
