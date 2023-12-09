package aoc.day6;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day6";
        InputStream inputStream = Helper.iStream(day, "int");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<Long> time = new ArrayList<>();
            List<Long> distance = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                String[] split = line.split(":");
                long values = Long.parseLong(Arrays.stream(split[1].split(" ")).filter(e -> !e.isBlank()).collect(Collectors.joining("")));
                if (split[0].equals("Time")) {
                    System.out.println("values: " + values);

                    time = List.of(values);
                } else {
                    System.out.println("values: " + values);
                    distance = List.of(values);
                }
                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("time: " + time);
            System.out.println("distance: " + distance);
            Map<Long, Long> lookup = IntStream.range(0, time.size()).boxed().collect(Collectors.toMap(time::get, distance::get));
            System.out.println("lookup: " + lookup);
            System.out.println();


            long startTime = System.nanoTime();
            // Call your function here


////            without improvement
//            List<Long> resultWays = lookup.keySet().stream()
//                    .map(e -> getDistance(e).stream()
//                            .filter(d -> d > lookup.get(e)).count()).toList();
//
//            System.out.println("resultWays: " + resultWays);
//            System.out.println();
//            Long result = resultWays.stream().reduce(1L, (acc, cur) -> acc * cur);
//            System.out.println("result: " + result);


//            with improvement
            Long overallTime = lookup.keySet().stream().findFirst().orElseThrow();
            Long overallDistance = lookup.values().stream().findFirst().orElseThrow();
            long ways = Arrays.stream(getPerformanceDistance(overallTime)).filter(d -> d > overallDistance).count();
            System.out.println("ways: " + ways);

            // Call your function here
            long endTime = System.nanoTime();
            long elapsedTimeInNanoseconds = endTime - startTime;
            double elapsedTimeInSeconds = (double) elapsedTimeInNanoseconds / 1_000_000_000.0;
            System.out.println("Elapsed Time (seconds): " + elapsedTimeInSeconds);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static long[] getPerformanceDistance(long time) {
        return LongStream.range(1, time).map(i -> (time - i) * i).toArray();
    }

    private static List<Long> getDistance(long time) {
        return LongStream.range(1, time).map(i -> (time - i) * i).boxed().toList();
    }
}
