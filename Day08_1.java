import java.util.List;
import java.util.stream.Collectors;

/**
 * Part one of
 * https://adventofcode.com/2020/day/8
 *
 * @author Nerijus
 */
public class Day08_1 {
    public static void main(String[] args) {
        System.out.println("Accumulator value: " + new Day08_1().getResult());
    }

    private int getResult() {
        return new Program(getInstructions()).run().value;
    }

    List<Instruction> getInstructions() {
        return Inputs.readStrings("Day08")
                .stream()
                .map(i -> new Instruction(i.split(" ")[0], Integer.parseInt(i.split(" ")[1])))
                .collect(Collectors.toList());
    }

    static class Program {
        int accumulator = 0;
        int position = 0;
        List<Instruction> instructions;

        public Program(List<Instruction> instructions) {
            this.instructions = instructions;
        }

        ProgramResult run() {
            Instruction currentInstruction;
            while (true) {
                if (position == instructions.size()) {
                    // exit
                    return new ProgramResult(true, accumulator);
                }
                currentInstruction = instructions.get(position);
                if (currentInstruction.executed) {
                    // infinite loop
                    return new ProgramResult(false, accumulator);
                }
                switch (currentInstruction.type) {
                    case "nop":
                        position = position + 1;
                        break;
                    case "acc":
                        accumulator = accumulator + currentInstruction.value;
                        position = position + 1;
                        break;
                    case "jmp":
                        position = position + currentInstruction.value;
                        break;
                    default:
                        throw new IllegalStateException("Unknown instruction: " + currentInstruction.type);
                }
                currentInstruction.executed = true;
            }
        }
    }

    static class Instruction {
        String type;
        int value;
        boolean executed;

        public Instruction(String type, int value) {
            this.type = type;
            this.value = value;
        }
    }

    static class ProgramResult {
        int value;
        boolean success;

        public ProgramResult(boolean success, int value) {
            this.success = success;
            this.value = value;
        }
    }
}
