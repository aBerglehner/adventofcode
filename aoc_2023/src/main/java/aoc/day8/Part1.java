package aoc.day8;

import aoc.helper.Helper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {

    public static void main(String[] args) throws Exception {
        String day = "day8";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<String> instructions = new ArrayList<>();
            Map<String, Node> nodeMap = new HashMap<>();
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
                    } else {
                        instructions.addAll(Arrays.asList(line.split("")));
                    }
                }
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("instructions: " + instructions);
//            Helper.prettyPrintList(nodeMap);
            Helper.prettyPrintMap(nodeMap, "nodeMap");

            String start = "AAA";
            int counter = 0;
            int pointer = 0;
            while (pointer < instructions.size() && counter < 50000000) {
                ++counter;
                String direction = instructions.get(pointer);
                if (direction.equals("L")) {
                    start = nodeMap.get(start).getL();
                } else {
                    start = nodeMap.get(start).getR();
                }
                if (start.equals("ZZZ")) {
                    break;
                }
                if (pointer + 1 == instructions.size()) {
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
}

// 13771 too low
// LLRLLRLLR
// BABABZ