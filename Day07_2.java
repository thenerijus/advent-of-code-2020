import java.util.Map;

/**
 * Part two of
 * https://adventofcode.com/2020/day/7
 *
 * @author Nerijus
 */
public class Day07_2 extends Day07_1 {

    public static void main(String[] args) {
        System.out.println("Bags are required inside gold bag: " + new Day07_2().getResult());
    }

    private int getResult() {
        Map<String, Bag> rules = getRules();
        Bag shinyGoldBag = rules.get("shiny gold");
        // 65822
        return shinyGoldBag.getBagCount(rules);
    }
}
