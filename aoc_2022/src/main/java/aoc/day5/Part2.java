package aoc.day5;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public enum Parts {
        FIRST,
        SECOND
    }

    public static void main(String[] args) throws Exception {
        String day = "day5";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Stack<String>> stacks = new ArrayList<>();
            int count = 9;
            while (count > 0) {
                count--;
                stacks.add(new Stack<>());
            }

            Parts part = Parts.FIRST;
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file

                if (line.isBlank()) {
                    part = Parts.SECOND;
                    System.out.println("stacks = " + stacks);
                } else if (part.equals(Parts.FIRST)) {
                    String[] split = line.split("");
                    for (int i = 0; i < split.length; i++) {
                        if (split[i].charAt(0) >= 'A' && split[i].charAt(0) <= 'Z') {
                            stacks.get(i / 4).add(0, split[i]);
                        }
                    }
//                    System.out.println("split = " + Arrays.toString(split));
                } else {
                    System.out.println(line);
                    List<String> procedure = Arrays.stream(line.split(" ")).filter(s -> s != null && s.matches("-?\\d+")).toList();
                    int moves = Integer.parseInt(procedure.get(0));
                    int fromStack = Integer.parseInt(procedure.get(1));
                    int toStack = Integer.parseInt(procedure.get(2));
                    System.out.println("moves = " + moves);
                    System.out.println("fromStack = " + fromStack);
                    System.out.println("toStack = " + toStack);

                    Stack<String> stackHolder = new Stack<>();
                    while (--moves >= 0) {
                        String pop = stacks.get(fromStack - 1).pop();
                        stackHolder.add(pop);
                    }
                    while (stackHolder.size() > 0) {
                        stacks.get(toStack - 1).add(stackHolder.pop());
                    }
                }
                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("stacks = " + stacks);
            System.out.println();

            String res = stacks.stream().map(e -> e.peek()).collect(Collectors.joining());
            System.out.println("res = " + res);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
