package aoc.day3;

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
        String day = "day3";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            int count = 0;
            final List<List<String>> groups = new ArrayList<>();
            List<Integer> res = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);

//                System.out.println("firstList = " + firstList);
                count++;
                groups.add(List.of(line.split("")));
                if (count % 3 == 0) {
                    int finalCount = count;
                    Integer sum = Arrays.stream(line.split(""))
                            .filter(e -> groups.get(finalCount - 3).contains(e) && groups.get(finalCount - 2).contains(e))
                            .distinct()
                            .flatMap(s -> {
                                if (s.charAt(0) >= 'a' && s.charAt(0) <= 'z') {
                                    return Stream.of(s.charAt(0) - 'a' + 1);
                                } else {
                                    return Stream.of(s.charAt(0) - 'A' + 27);
                                }
                            }).reduce(0, Integer::sum);
                    res.add(sum);
                }


//                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println(0 % 3);

            System.out.println("res = " + res);
            int sum = res.stream().mapToInt(e -> e).sum();
            System.out.println("sum = " + sum);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
