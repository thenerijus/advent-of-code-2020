import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Part one of
 * https://adventofcode.com/2020/day/7
 *
 * @author Nerijus
 */
public class Day07_1 {
    private static final String RULE_PATTERN = "([\\w ]+?) bags contain(( (\\d) ([\\w ]+?) bags?,?)+| no other bags)";
    private static final String BAG_PATTERN = "(\\d) ([\\w ]+?) bags?,?";

    public static void main(String[] args) {
        System.out.println("Bag colors that can eventually contain at least one shiny gold bag: " + new Day07_1().getResult());
    }

    private int getResult() {
        Map<String, Bag> rules = getRules();

        Set<String> canContainGoldBag = new HashSet<>();
        int previousSize;
        do {
            previousSize = canContainGoldBag.size();
            for (Bag rule : rules.values()) {
                if (rule.contains.stream().anyMatch(b -> b.color.equals("shiny gold") || canContainGoldBag.contains(b.color))) {
                    canContainGoldBag.add(rule.color);
                }
            }
        } while (previousSize != canContainGoldBag.size());

        return canContainGoldBag.size();
    }

    Map<String, Bag> getRules() {
        Map<String, Bag> rules = new HashMap<>();
        Pattern rulePattern = Pattern.compile(RULE_PATTERN);
        Pattern bagPattern = Pattern.compile(BAG_PATTERN);
        for (String rule : Inputs.readStrings("Day07")) {
            Bag bag = new Bag();
            bag.amount = 1;
            Matcher matcher = rulePattern.matcher(rule);
            if (matcher.find()) {
                bag.color = matcher.group(1);
                String innerBags = matcher.group(2);
                Matcher bagMatcher = bagPattern.matcher(innerBags);
                while (bagMatcher.find()) {
                    Bag innerBag = new Bag();
                    innerBag.amount = Integer.parseInt(bagMatcher.group(1));
                    innerBag.color = bagMatcher.group(2);
                    bag.contains.add(innerBag);
                }
            } else {
                throw new IllegalStateException("Could not match: " + rule);
            }
            rules.put(bag.color, bag);
        }
        return rules;
    }

    static class Bag {
        int amount;
        String color;
        List<Bag> contains = new ArrayList<>();

        int getBagCount(Map<String, Bag> rules) {
            if (contains.isEmpty()) {
                return 0;
            }
            return contains
                    .stream()
                    .mapToInt(b -> (b.amount * rules.get(b.color).getBagCount(rules)) + b.amount)
                    .sum();
        }
    }
}
