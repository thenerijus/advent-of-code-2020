import java.util.Map;
import java.util.stream.Collectors;

/**
 * Part two of
 * https://adventofcode.com/2020/day/5
 *
 * @author Nerijus
 */
public class Day05_2 extends Day05_1 {
    public static void main(String[] args) {
        System.out.println("ID of my seat: " + new Day05_2().getResult());
    }

    private int getResult() {
        int maxId = 832;
        Map<Integer, BoardingPass> allBoardingPasses = getAllBoardingPasses()
                .stream()
                .collect(Collectors.toMap(BoardingPass::getId, bp -> bp));

        for (int i = 0; i < maxId; i++) {
            if (allBoardingPasses.containsKey(i)) {
                continue;
            }
            if (allBoardingPasses.containsKey(i - 1) && allBoardingPasses.containsKey(i + 1)) {
                return i;
            }
        }

        throw new IllegalStateException("Could not find my seat ID");
    }
}
