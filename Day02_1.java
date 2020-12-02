import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Part one of
 * https://adventofcode.com/2020/day/2
 *
 * @author Nerijus
 */
public class Day02_1 {
    private static final String PATTERN = "(\\d+)-(\\d+) (\\w): (\\w+)";

    public static void main(String[] args) {
        System.out.println("Valid passwords: " + new Day02_1().getResult());
    }

    private long getResult() {
        return getPasswords()
                .stream()
                .filter(Password::matchesCriteriaOne)
                .count();
    }

    List<Password> getPasswords() {
        return Inputs.readStrings("Day02")
                .stream()
                .map(this::getPassword)
                .collect(Collectors.toList());
    }

    private Password getPassword(String entry) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(entry);
        if (matcher.find()) {
            return new Password(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    matcher.group(3),
                    matcher.group(4));
        } else {
            throw new IllegalStateException("Could not match expected pattern: " + entry);
        }
    }

    static class Password {
        int min;
        int max;
        String requiredChar;
        String password;

        public Password(int min, int max, String requiredChar, String password) {
            this.min = min;
            this.max = max;
            this.requiredChar = requiredChar;
            this.password = password;
        }

        boolean matchesCriteriaOne() {
            long charCount = Arrays.stream(password.split("")).filter(p -> p.equals(requiredChar)).count();
            return charCount >= min && charCount <= max;
        }

        boolean matchesCriteriaTwo() {
            String[] parts = password.split("");
            boolean matchesFirst = parts.length >= min && parts[min - 1].equals(requiredChar);
            boolean matchesSecond = parts.length >= max && parts[max - 1].equals(requiredChar);
            return (matchesFirst && !matchesSecond) || (!matchesFirst && matchesSecond);
        }
    }
}
