package aoc.day3;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day3";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Integer> res = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                int mid = line.length() / 2;
                String firstHalf = line.substring(0, mid);
                List<String> firstList = List.of(firstHalf.split(""));
//                System.out.println("firstList = " + firstList);
                String secondHalf = line.substring(mid);
                Integer sum = Arrays.stream(secondHalf.split(""))
                        .filter(firstList::contains)
                        .distinct()
                        .flatMap(s -> {
                            if (s.charAt(0) >= 'a' && s.charAt(0) <= 'z') {
                                return Stream.of(s.charAt(0) - 'a' + 1);
                            } else {
                                return Stream.of(s.charAt(0) - 'A' + 27);

                            }
                        }).reduce(0, Integer::sum);
                res.add(sum);


//                System.out.println("---------------------");
            }
            System.out.println();

            System.out.println("res = " + res);
            int sum = res.stream().mapToInt(e -> e).sum();
            System.out.println("sum = " + sum);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
