package aoc.day2;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day2";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Integer> result = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                String[] split = line.split(":");
//                System.out.println(Arrays.toString(split));
                if (split.length == 2) {
                    String game = split[0].split(" ")[1];
                    String bag = split[1];
//                    System.out.println("game: " + game);
                    Map<String, Integer> collect = Arrays.stream(bag.replaceAll(";", ",").split(",")).map(String::trim).map(e -> e.split("\\s+")).collect(Collectors.toMap(e -> e[1], e -> Integer.valueOf(e[0]), Integer::max));
//                    System.out.println(collect);

                    int powerCubes = 1;
                    if (collect.containsKey("red")) {
                        powerCubes *= collect.get("red");
                    }
                    if (collect.containsKey("green")) {
                        powerCubes *= collect.get("green");
                    }
                    if (collect.containsKey("blue")) {
                        powerCubes *= collect.get("blue");
                    }

                    result.add(powerCubes);

                }
//                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("result: " + result);
            System.out.println();
            System.out.println(result.stream().reduce(0, Integer::sum));


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
//197 too low