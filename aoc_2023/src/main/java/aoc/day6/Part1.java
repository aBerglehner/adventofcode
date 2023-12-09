package aoc.day6;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day6";
        InputStream inputStream = Helper.iStream(day, "i");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<Integer> time = new ArrayList<>();
            List<Integer> distance = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                String[] split = line.split(":");
                List<Integer> values = Arrays.stream(split[1].split(" ")).filter(e -> !e.isBlank()).map(Integer::parseInt).collect(Collectors.toList());
                if (split[0].equals("Time")) {
                    System.out.println("values: " + values);
                    time = values;
                } else {
                    System.out.println("values: " + values);
                    distance = values;
                }
                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("time: " + time);
            System.out.println("distance: " + distance);
            Map<Integer, Integer> lookup = IntStream.range(0, time.size()).boxed().collect(Collectors.toMap(time::get, distance::get));
            System.out.println("lookup: " + lookup);
            System.out.println();
            Set<Integer> resultWays = lookup.keySet().stream()
                    .map(e -> getDistance(e).stream()
                            .filter(d -> d > lookup.get(e)).toList().size()).collect(Collectors.toSet());
            System.out.println("resultWays: " + resultWays);
            System.out.println();
            Integer result = resultWays.stream().reduce(1, (acc, cur) -> acc * cur);
            System.out.println("result: " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static List<Integer> getDistance(int time) {
        return IntStream.range(1, time).map(i -> (time - i) * i).boxed().toList();
    }
}
