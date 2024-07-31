package aoc.day1;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Part2 {
    //    public record
    public static void main(String[] args) throws Exception {
        String day = "day1";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<List<Integer>> outArr = new ArrayList<>();
            List<Integer> innerArr = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                if (line.isBlank()) {
                    outArr.add(innerArr.stream().toList());
                    innerArr.clear();
                } else {
                    innerArr.add(Integer.valueOf(line));
                }


//                    System.out.println("---------------------");
            }
            outArr.add(innerArr.stream().toList());
            System.out.println();
            System.out.println("outArr = " + outArr);
            Integer result = outArr.stream().map(arr -> arr.stream().reduce(0, (acc, cur) -> acc + cur)).sorted(Comparator.reverseOrder()).limit(3).reduce(0, Integer::sum);
            System.out.println("result = " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
