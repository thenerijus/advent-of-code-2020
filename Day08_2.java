import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Part two of
 * https://adventofcode.com/2020/day/8
 *
 * @author Nerijus
 */
public class Day08_2 extends Day08_1 {
    public static void main(String[] args) {
        System.out.println("Accumulator value (2): " + new Day08_2().getResult());
    }

    private int getResult() {
        Map<Integer, Instruction> replacements = getPossibleReplacements(getInstructions());

        for (Map.Entry<Integer, Instruction> replacement : replacements.entrySet()) {
            List<Instruction> instructions = getInstructions();
            instructions.remove((int)replacement.getKey());
            instructions.add(replacement.getKey(), replacement.getValue());
            ProgramResult result = new Program(instructions).run();
            if (result.success) {
                return result.value;
            }
        }

        throw new IllegalStateException("Did not found a proper replacement");
    }

    private Map<Integer, Instruction> getPossibleReplacements(List<Instruction> instructions) {
        Map<Integer, Instruction> replacements = new HashMap<>();
        IntStream.range(0, instructions.size())
                .forEach(idx -> {
                    Instruction instruction = instructions.get(idx);
                    if ("jmp".equals(instruction.type)) {
                        replacements.put(idx, new Instruction("nop", instruction.value));
                    } else if ("nop".equals(instruction.type)) {
                        replacements.put(idx, new Instruction("jmp", instruction.value));
                    }
                });
        return replacements;
    }
}
