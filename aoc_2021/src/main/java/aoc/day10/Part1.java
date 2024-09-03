package aoc.day10;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day10";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<String>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                matrix.add(Arrays.stream(line.split("")).toList());
//                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(matrix);

            Map<String, String> lookUp = Map.of(")", "(", "]", "[", "}", "{", ">", "<");
            String openOnes = "([{<";
            List<String> res = new ArrayList<>();
            for (List<String> row : matrix) {
                Stack<String> stack = new Stack<>();
                for (int col = 0; col < row.size(); col++) {
                    String cur = row.get(col);
                    if (openOnes.contains(cur)) {
                        stack.add(cur);
                    } else {
                        if (!lookUp.get(cur).equals(stack.pop())) {
                            res.add(cur);
                            break;
                        }

                    }
                }
            }
            System.out.println("res = " + res);

            Map<String, Integer> points = Map.of(")", 3, "]", 57, "}", 1197, ">", 25137);
            Integer sum = res.stream().map(points::get).reduce(0, Integer::sum);
            System.out.println("sum = " + sum);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
