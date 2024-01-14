package aoc.day5;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
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
                if (min3Vowels(line) && oneLetterTwiceARow(line) && noBadStrings(line)) {
                    ++sum;
                }
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("sum = " + sum);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static boolean min3Vowels(String str) {
        List<String> vowels = List.of("a", "e", "i", "o", "u");
        long vowelCount = Arrays.stream(str.split("")).filter(vowels::contains).count();
        return vowelCount >= 3;
    }

    private static boolean oneLetterTwiceARow(String str) {
        String[] strArr = str.split("");
        for (int i = 1; i < strArr.length; i++) {
            String prev = strArr[i - 1];
            String cur = strArr[i];
            if (prev.equals(cur)) {
                return true;
            }
        }
        return false;
    }

    private static boolean noBadStrings(String str) {
        List<String> badStrings = List.of("ab", "cd", "pq", "xy");
        String[] strArr = str.split("");
        for (int i = 0; i < strArr.length - 1; i++) {
            String substring = str.substring(i, i + 2);
            if (badStrings.contains(substring)) {
                return false;
            }
        }
        return true;
    }
}

//min 3 vowels aeiou
//one letter twice in a row
// not contain ab, cd, pq, or xy