package aoc.day10;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Part2 {
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
            List<List<String>> incompleteMatrix = new ArrayList<>();
            for (List<String> row : matrix) {
                Stack<String> stack = new Stack<>();
                for (int col = 0; col < row.size(); col++) {
                    String cur = row.get(col);
                    if (openOnes.contains(cur)) {
                        stack.add(cur);
                    } else {
                        if (!lookUp.get(cur).equals(stack.pop())) {
                            break;
                        }
                    }
                    if (col == row.size() - 1) {
                        incompleteMatrix.add(row);
                    }
                }
            }
            Helper.prettyPrintList(incompleteMatrix, "incompleteMatrix");

            Map<String, String> reverseLookup = Map.of("(", ")", "[", "]", "{", "}", "<", ">");
            List<List<String>> resMatrix = new ArrayList<>();
            for (List<String> row : incompleteMatrix) {
                List<String> res = new ArrayList<>();
                Stack<String> stack = new Stack<>();
                for (int col = 0; col < row.size(); col++) {
                    String cur = row.get(col);
                    if (openOnes.contains(cur)) {
                        stack.add(cur);
                    } else {
                        stack.pop();
                    }
                }
                System.out.println("stack = " + stack);
                while (stack.size() > 0) {
                    String s = reverseLookup.get(stack.pop());
                    res.add(s);
                }
                resMatrix.add(res);
            }
            Helper.prettyPrintList(resMatrix, "resMatrix");

            Map<String, Integer> points = Map.of(")", 1, "]", 2, "}", 3, ">", 4);
            List<Long> resSum = new ArrayList<>();
            for (List<String> row : resMatrix) {
                long score = 0;
                for (String col : row) {
                    score = score * 5 + points.get(col);
                }
                resSum.add(score);
            }
            System.out.println("resSum = " + resSum);
            long[] array = resSum.stream().mapToLong(e -> e).sorted().toArray();
            System.out.println("array = " + Arrays.toString(array));
            long middle = array[array.length / 2];
            System.out.println("middle = " + middle);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}

//