import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Part one of
 * https://adventofcode.com/2020/day/10
 *
 * @author Nerijus
 */
public class Day10_1 {
    public static void main(String[] args) {
        System.out.println("Number of 1-jolt differences multiplied by the number of 3-jolt differences: " + new Day10_1().getResult());
    }

    private int getResult() {
        List<Integer> adapters = Inputs.readInts("Day10");
        List<Integer> adapterChain = new ArrayList<>();

        int currentJolts = 0;

        // add outlet
        adapterChain.add(currentJolts);

        while (!adapters.isEmpty()) {
            Integer nextAdapter = getNextAdapter(adapters, currentJolts);

            currentJolts = nextAdapter;
            adapters.remove(nextAdapter);
            adapterChain.add(nextAdapter);
        }

        // add the device itself
        adapterChain.add(adapterChain.get(adapterChain.size() - 1) + 3);

        return calculateResult(adapterChain);
    }

    private int calculateResult(List<Integer> adapterChain) {
        int differenceOne = 0;
        int differenceThree = 0;
        Integer previous = null;
        for (Integer adapter : adapterChain) {
            if (previous == null) {
                previous = adapter;
                continue;
            }
            if (adapter - previous == 1) {
                differenceOne = differenceOne + 1;
            } else if (adapter - previous == 3) {
                differenceThree = differenceThree + 1;
            }
            previous = adapter;
        }
        System.out.println("Difference 1: " + differenceOne);
        System.out.println("Difference 3: " + differenceThree);
        return differenceOne * differenceThree;
    }

    Integer getNextAdapter(List<Integer> adapters, int currentJolts) {
        List<Integer> potentialAdapters = getPotentialAdapters(adapters, currentJolts);
        return potentialAdapters
                .stream()
                .sorted()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find matching adapter"));
    }

    List<Integer> getPotentialAdapters(List<Integer> adapters, int currentJolts) {
        return adapters
                .stream()
                .filter(a -> {
                    int difference = a - currentJolts;
                    return difference > 0 && difference <= 3;
                })
                .collect(Collectors.toList());
    }
}
