package aoc.day1;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day1";
        InputStream inputStream = Helper.iStream(day, "test");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Integer> nums = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                nums.add(Integer.parseInt(line));
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("nums = " + nums);
            List<Integer> result = new ArrayList<>();
            int l = 0;
            int sum = nums.get(0) + nums.get(1);
            for (int i = 2; i < nums.size(); i++) {
                sum += nums.get(i);
                result.add(sum);
                sum -= nums.get(l++);
            }
            Helper.prettyPrintList(result);

            long count = IntStream.range(1, result.size()).filter(i -> result.get(i) > result.get(i - 1)).count();
            System.out.println("count = " + count);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
