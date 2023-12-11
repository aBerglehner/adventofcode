package aoc.day8;

import aoc.helper.Helper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws Exception {
        String day = "day8";
        InputStream inputStream = Helper.iStream(day, "te");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<String> instructions = new ArrayList<>();
            char[] ins = new char[0];
            Map<String, Node> nodeMap = new HashMap<>();
            List<String> st = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                if (!line.isBlank()) {
                    if (line.contains("=")) {
//                        System.out.println(line);
                        String[] split = line.split("=");
                        String key = split[0].trim();
                        List<String> nodes = Arrays.stream(split[1].trim().substring(1, 9).split(",")).map(String::trim).collect(Collectors.toList());
                        nodeMap.put(key, new Node(nodes.get(0), nodes.get(1)));
                        if (key.endsWith("A")) {
                            st.add(key);
                        }
                    } else {
                        ins = line.toCharArray();
                        instructions.addAll(Arrays.asList(line.split("")));
                    }
                }
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("instructions: " + instructions);
//            Helper.prettyPrintList(nodeMap);
            Helper.prettyPrintMap(nodeMap, "nodeMap");

//            System.out.println("starts = " + starts);
//            System.out.println();

//            String start = "AAA";
            long startTime = System.nanoTime();
            long endTime;

//            String[] ins = instructions.toArray(new String[0]);
//            instructions.stream()
            instructions.toArray(new String[0]);
            String[] starts = st.toArray(new String[0]);

            // Calculate and print the elapsed time in seconds
            int counter = 0;
            int pointer = 0;
            while (pointer < ins.length) {
                if (counter == 10000000) {
                    System.out.println(10_000_000);
                    System.out.println("starts = " + Arrays.toString(starts));
                    endTime = System.nanoTime();
                    double elapsedTimeSeconds = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
                    System.out.println("Method execution time: " + elapsedTimeSeconds + " seconds");
                }
                if (counter == 50000000) {
                    System.out.println(50_000_000);
                    System.out.println("starts = " + Arrays.toString(starts));
                    endTime = System.nanoTime();
                    double elapsedTimeSeconds = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
                    System.out.println("Method execution time: " + elapsedTimeSeconds + " seconds");
                }
                if (counter == 100000000) {
                    System.out.println(100_000_000);
                    System.out.println("starts = " + Arrays.toString(starts));
                    endTime = System.nanoTime();
                    double elapsedTimeSeconds = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
                    System.out.println("Method execution time: " + elapsedTimeSeconds + " seconds");
                }
                if (counter == 200000000) {
                    System.out.println(200_000_000);
                    System.out.println("starts = " + Arrays.toString(starts));
                    endTime = System.nanoTime();
                    double elapsedTimeSeconds = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
                    System.out.println("Method execution time: " + elapsedTimeSeconds + " seconds");
                }
                if (counter == 500000000) {
                    System.out.println(500_000_000);
                    System.out.println("starts = " + Arrays.toString(starts));
                    endTime = System.nanoTime();
                    double elapsedTimeSeconds = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
                    System.out.println("Method execution time: " + elapsedTimeSeconds + " seconds");
                    break;
                }
                ++counter;
//                String direction = instructions.get(pointer);
                char direction = ins[pointer];

                for (int i = 0; i < starts.length; i++) {
                    String start = starts[i];
                    start = getString(direction, start, nodeMap);
//                    starts.set(i, start);
                    starts[i] = start;
                }
//                boolean endPoint = starts.stream().allMatch(e -> e.endsWith("Z"));
                boolean endPoint = true;
                for (String start : starts) {
                    if (!start.endsWith("Z")) {
                        endPoint = false;
                        break;
                    }
                }
                if (endPoint) {
                    System.out.println("starts = " + Arrays.toString(starts));
                    break;
                }


                if (pointer + 1 == ins.length) {
                    pointer = 0;
                } else {
                    ++pointer;
                }
            }

            System.out.println("pointer = " + counter);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static String getString(char direction, String start, Map<String, Node> nodeMap) {
        if (direction == 'L') {
            start = nodeMap.get(start).getL();
        } else {
            start = nodeMap.get(start).getR();
        }
        return start;
    }
}

// 13771 too low
// LLRLLRLLR
// BABABZ