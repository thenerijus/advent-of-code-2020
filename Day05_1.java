import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Part one of
 * https://adventofcode.com/2020/day/5
 *
 * @author Nerijus
 */
public class Day05_1 {
    public static void main(String[] args) {
        System.out.println("Highest seat ID on a boarding pass: " + new Day05_1().getResult());
    }

    private long getResult() {
        return getAllBoardingPasses()
                .stream()
                .mapToInt(BoardingPass::getId)
                .max()
                .orElseThrow(() -> new IllegalStateException("Could not find max ID"));
    }

    List<BoardingPass> getAllBoardingPasses() {
        return Inputs.readStrings("Day05")
                .stream()
                .map(BoardingPass::new)
                .collect(Collectors.toList());
    }

    static class BoardingPass {
        String instructions;
        int row;
        int column;

        public BoardingPass(String instructions) {
            this.instructions = instructions;
            this.row = calculateRow(instructions.substring(0, 7));
            this.column = calculateColumn(instructions.substring(7));
        }

        private int calculateRow(String instructions) {
            String[] steps = instructions.split("");
            int from = 0;
            int to = 127;

            for (String step : steps) {
                if ("B".equals(step)) {
                    // upper
                    if (to - from == 1) {
                        return to;
                    }
                    from = from + ((to - from) / 2) + 1;
                } else {
                    // lower
                    if (to - from == 1) {
                        return from;
                    }
                    to = to - ((to - from) / 2) - 1;
                }
            }

            throw new IllegalStateException("Could not calculate row");
        }

        private int calculateColumn(String instructions) {
            String[] steps = instructions.split("");
            int from = 0;
            int to = 7;

            for (String step : steps) {
                if ("R".equals(step)) {
                    // upper
                    if (to - from == 1) {
                        return to;
                    }
                    from = from + ((to - from) / 2) + 1;
                } else {
                    // lower
                    if (to - from == 1) {
                        return from;
                    }
                    to = to - ((to - from) / 2) - 1;
                }
            }

            throw new IllegalStateException("Could not calculate column");
        }

        int getId() {
            return row * 8 + column;
        }

        @Override
        public String toString() {
            return String.format("%s: row %d, column %d, seat ID %d", instructions, row, column, getId());
        }
    }
}
