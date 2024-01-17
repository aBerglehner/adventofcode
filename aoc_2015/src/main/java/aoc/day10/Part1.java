package aoc.day10;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day10";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                List<String> list = Arrays.asList(line.split(""));
                String s = applyLookAndSay(list, 40);
//                System.out.println("s = " + s);
                System.out.println("s.length() = " + s.length());
//                    System.out.println("---------------------");
            }
            System.out.println();


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static String applyLookAndSay(List<String> str, int counter) {
        if (counter == 0) {
            return String.join("", str);
        }
        //modify
        List<String> newStr = new ArrayList<>();
        for (int i = 0; i < str.size(); i++) {
            int j = i + 1;
            if (i < str.size() - 1) {
                while (j < str.size() && str.get(i).equals(str.get(j))) {
                    j++;
                }
            }
            newStr.add(String.valueOf(j - i));
            newStr.add(str.get(i));
            i = j - 1;
        }

        return applyLookAndSay(newStr, counter - 1);
    }
}
