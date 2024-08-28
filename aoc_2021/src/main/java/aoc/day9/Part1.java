package aoc.day9;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day9";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<Integer>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                matrix.add(Arrays.stream(line.split("")).map(Integer::parseInt).toList());
//                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(matrix);
            List<Integer> lowPoints = new ArrayList<>();
            int rows = matrix.size();
            int cols = matrix.get(0).size();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    //todo look around
                    if (isLowest(matrix, row, col)) {
                        Integer cur = matrix.get(row).get(col);
                        lowPoints.add(cur);
                    }

                }
            }
            System.out.println("lowPoints = " + lowPoints);
            Integer res = lowPoints.stream().map(e -> e + 1).reduce(0, Integer::sum);
            System.out.println("res = " + res);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static boolean isLowest(List<List<Integer>> matrix, int row, int col) {
        Integer cur = matrix.get(row).get(col);
        //up
        if (row + 1 < matrix.size() && matrix.get(row + 1).get(col) <= cur) {
            return false;
        }
        //down
        if (row - 1 >= 0 && matrix.get(row - 1).get(col) <= cur) {
            return false;
        }
        //right
        if (col + 1 < matrix.get(0).size() && matrix.get(row).get(col + 1) <= cur) {
            return false;
        }
        //left
        if (col - 1 >= 0 && matrix.get(row).get(col - 1) <= cur) {
            return false;
        }
        return true;
    }
}
// 1568 too high