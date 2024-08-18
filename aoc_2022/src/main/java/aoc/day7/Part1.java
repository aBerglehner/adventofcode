package aoc.day7;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day7";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            Stack<String> path = new Stack<>();
            Map<String, Integer> dirs = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                if (line.startsWith("$ cd")) {
                    if (line.startsWith("$ cd ..")) { // go up to parent one
                        path.pop();
                    } else { // go to child one
                        String dirName = line.split(" ")[2];
                        System.out.println("dirName = " + dirName);
                        path.add(dirName);
                    }

                } else if (line.startsWith("$ ls")) {
                    continue;
                } else if (line.startsWith("dir")) {
                    continue;
                } else {
                    String size = line.split(" ")[0];
                    for (int i = 0; i < path.size(); i++) {
                        String curPath = path.stream().limit(i + 1).collect(Collectors.joining("/"));
                        if (!dirs.containsKey(curPath)) {
                            dirs.put(curPath, 0);
                        }
                        Integer curValue = dirs.get(curPath);
                        dirs.put(curPath, curValue + Integer.parseInt(size));
                    }
                }
//                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintMap(dirs, "dirs");
            System.out.println();
            Integer res = dirs.values().stream().filter(e -> e <= 100000).reduce(0, Integer::sum);
            System.out.println("res = " + res);

            System.out.println();


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

}