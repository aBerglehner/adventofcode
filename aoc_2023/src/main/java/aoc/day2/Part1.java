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

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day2";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            final int redCubes = 12;
            final int greenCubes = 13;
            final int blueCubes = 14;
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

                    int notValid = 0;
                    if (collect.containsKey("red")) {
                        notValid += collect.get("red") <= redCubes ? 0 : 1;
                    }
                    if (collect.containsKey("green")) {
                        notValid += collect.get("green") <= greenCubes ? 0 : 1;
                    }
                    if (collect.containsKey("blue")) {
                        notValid += collect.get("blue") <= blueCubes ? 0 : 1;
                    }

                    if (notValid == 0) {
                        result.add(Integer.valueOf(game));
                    }

                }
//                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println(result);
            System.out.println();
            System.out.println(result.stream().reduce(0, Integer::sum));


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
//197 too low