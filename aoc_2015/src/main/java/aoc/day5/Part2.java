package aoc.day5;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day5";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int sum = 0;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                if (firstAndThirdAreEqual(line) && hasTwoPairs(line)) {
                    ++sum;
                }
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("sum = " + sum);

            System.out.println();
            String check = "phdqqxleqdjfgfbg";
            boolean checkPairs = hasTwoPairs(check);
            System.out.println("checkPairs = " + checkPairs);
            boolean checkEqual = firstAndThirdAreEqual(check);
            System.out.println("checkEqual = " + checkEqual);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static boolean hasTwoPairs(String str) {
        String[] strArr = str.split("");
        //string = pair | Integer = start -> start Math.abs > 1
        Map<String, List<Integer>> checker = new HashMap<>();
        for (int i = 0; i < strArr.length - 1; i += 1) {
            String pair = str.substring(i, i + 2);
            if (checker.containsKey(pair)) {
                for (Integer start : checker.get(pair)) {
                    if (Math.abs(i - start) > 1) {
                        return true;
                    }
                }
            }
            if (!checker.containsKey(pair)) {
                checker.put(pair, new ArrayList<>());
            }
            checker.get(pair).add(i);
        }
        return false;
    }

    private static boolean firstAndThirdAreEqual(String str) {
        String[] strArr = str.split("");
        for (int i = 1; i < strArr.length - 1; i++) {
            String prev = strArr[i - 1];
            String next = strArr[i + 1];
            if (prev.equals(next)) {
                return true;
            }
        }
        return false;
    }

}

// first and third are the same
// two pairs aafdasfdsaa -> aa | xyxy -> xy
//68 is wrong