package aoc.day7;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day7";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Integer> crabs = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                crabs = Arrays.stream(line.split(",")).map(Integer::parseInt).sorted().toList();
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("crabs = " + crabs);
            int len = crabs.size();
            System.out.println("crabs.size() = " + len);
            int l = crabs.get(0);
            int r = crabs.get(len - 1);

            int result = Integer.MAX_VALUE;
            while (l < r) {
                int sumL = getSum(crabs, l);
                int sumR = getSum(crabs, r);
                int mid = (l + r) / 2;
                int sumMid = getSum(crabs, mid);
                result = Math.min(sumMid, result);

                if (sumL < sumR) {
                    result = Math.min(result, sumL);
                    r = mid;
                } else {
                    result = Math.min(result, sumR);
                    l = mid + 1;
                }
            }
            System.out.println("result = " + result);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int getSum(List<Integer> crabs, int num) {
        int sum = 0;
        for (Integer crab : crabs) {
            int adder = 1;
            int rounds = Math.abs(crab - num);
            while (rounds-- > 0) {
                sum += adder;
                adder++;
            }
        }
        return sum;
    }
}
