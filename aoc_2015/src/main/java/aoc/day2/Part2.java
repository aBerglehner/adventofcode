package aoc.day2;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day2";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<Integer> result = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                int[] array = Arrays.stream(line.split("x")).mapToInt(Integer::parseInt).toArray();
                int l = array[0];
                int w = array[1];
                int h = array[2];
                List<Integer> sortedList = Stream.of(l, w, h).sorted().toList();
                int min = sortedList.get(0) + sortedList.get(0) + sortedList.get(1) + sortedList.get(1);
                int bow = l * w * h;
                result.add(min + bow);


//                    System.out.println("---------------------");
            }
            System.out.println();

            Integer sum = result.stream().reduce(Integer::sum).orElse(-1);
            System.out.println("sum = " + sum);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}

//3753470 too high