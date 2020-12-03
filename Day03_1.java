import common.Coordinates;

import java.util.List;

/**
 * Part one of
 * https://adventofcode.com/2020/day/3
 *
 * @author Nerijus
 */
public class Day03_1 {
    public static void main(String[] args) {
        System.out.println("Tree count: " + new Day03_1().getResult());
    }

    private long getResult() {
        Rule rule = new Rule(3, 1);
        return getTreeCount(rule);
    }

    long getTreeCount(Rule rule) {
        long treeCount = 0;
        String[][] map = getMap();
        Coordinates currentPosition = new Coordinates(0, 0);
        while (currentPosition.y < map.length) {
            String location = map[currentPosition.y][currentPosition.x];
            if ("#".equals(location)) {
                // is a tree
                treeCount = treeCount + 1;
            }
            currentPosition = getNextPosition(currentPosition, map, rule);
        }

        return treeCount;
    }

    private Coordinates getNextPosition(Coordinates currentPosition, String[][] map, Rule rule) {
        int currentX = currentPosition.x;
        int currentY = currentPosition.y;
        if (currentX + rule.right >= map[0].length) {
            // of bounds, calculate position in another map
            return new Coordinates((currentX + rule.right) - map[0].length, currentY + rule.down);
        } else {
            return new Coordinates(currentX + rule.right, currentY + rule.down);
        }
    }

    private String[][] getMap() {
        List<String> rows = Inputs.readStrings("Day03");
        String[][] map = new String[rows.size()][rows.get(0).length()];
        for (int y = 0; y < rows.size(); y++) {
            String[] cells = rows.get(y).split("");
            System.arraycopy(cells, 0, map[y], 0, cells.length);
        }
        return map;
    }

    static class Rule {
        int right;
        int down;

        public Rule(int right, int down) {
            this.right = right;
            this.down = down;
        }
    }
}
